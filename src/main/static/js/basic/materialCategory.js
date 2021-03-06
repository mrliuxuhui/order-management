/**
 * Created by 刘旭辉 on 2017/9/18.
 */

$(function () {

    //modal
    $("#modal-add").on("hidden.bs.modal", function() {
        $(this).removeData("hidden.bs.modal");
        $("#data-form")[0].reset();
    });

    $("#modal-add").on("show.bs.modal", function() {
        var materialCategoryId = $(this).find("#materialCategoryId").val();
        var numReg = new RegExp("^\\d+$");
        if(null!=materialCategoryId&&numReg.test(materialCategoryId)){
            $(this).find(".modal-title").text("更新食材分类");
            $("#data-form").attr("action","/admin/api/materialCategory/"+materialCategoryId);
            $("#data-form").attr("method","put");

        }else{
            $(this).find(".modal-title").text("添加食材分类");
            $("#data-form").attr("action","/admin/api/materialCategory");
            $("#data-form").attr("method","post");
        }
    });

    //validator
    $("#data-form").bootstrapValidator({
        message: '输入不合法',
        submitHandler: function (valiadtor, loginForm, submitButton) {

        },
        fields:{
            name:{
                validators:{
                    notEmpty:{
                        message:"不可为空"
                    },
                    stringLength:{
                        max:30,
                        message:"最大长度不可超过30"
                    }
                }
            }
        }
    });

    $("#submitBtn").on("click",function(){
        var loginForm = $("#data-form");
        var validator = $("#data-form").data("bootstrapValidator");
        validator.validate();
        if(validator.isValid()){
            var action = loginForm.attr("action");
            //var createRex = new RegExp("materialCategory$");
            var updateRex = new RegExp("materialCategory/\\d+$");
            var method = "post";
            if(updateRex.test(action)){
                method = "put";
            }
            $.ajax({
                url:action,
                type:method,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                dataType:"json",
                data: loginForm.serializeJSON(),
                success:function(result){
                    console.log(result);
                    $("#modal-add").modal("hide");
                    table.draw();
                },
                error:function(req,error){
                    console.log(error);
                }
            });
        }
    });

    //批量删除
    $("#modal-del").on("show.bs.modal", function() {
        var selected = table.rows('.selected').data().length;
        if(null!=selected&&selected>0){
            $(this).find("div.modal-body > p").text("是否确认全部删除？");
        }else{
            $(this).find("div.modal-body > p").text("没有选择要删除的记录！");
        }
    });

    $("#delBtn").on("click",function(){
        var data = table.rows('.selected').data();
        var materialCategoryIds = new Array();
        $.each(data, function (index, value) {
            materialCategoryIds.push(value.id);
        });

        $.ajax({
            url:"/admin/api/materialCategory",
            type:"delete",
            dataType:"json",
            data:JSON.stringify(materialCategoryIds),
            success:function(result){
                table.draw();
            }
        });
    });

    //单行
    $("#example1 tbody").on("click", "#editRowBtn" ,function(e){
        e.stopPropagation();
        var data = table.row($(this).parents("tr")).data();
        if(null==data||null==data.id){
            return;
        }
        var id = data.id;
        $.ajax({
            dataType:"json",
            url:"/admin/api/materialCategory/"+id,
            success:function(result){
                if(null==result){
                    return;
                }
                $('#data-form input[name="name"]').val(result.name);
                $('#data-form text[name="profile"]').text(result.profile);

                $("#materialCategoryId").val(id);

                $("#modal-add").modal("show");
            }

        });

    });

    //del
    $("#example1 tbody").on("click", "#delRowBtn",function(e){
        e.stopPropagation();
        var data = table.row($(this).parents("tr")).data();
        if(null==data||null==data.id){
            return;
        }
        var id = data.id;
        $.ajax({
            type:"delete",
            dataType:"json",
            url:"/admin/api/materialCategory/"+id,
            success:function(result){
                table.draw();
            }

        });

    });

    //table
    var tpl = '{{each func as button}}'+
        '<button type="button" class="btn btn-{{button.type}} btn-sm" id="{{button.id}}">{{button.name}}</button>' +
        '{{/each}}';
    //预编译模板
    var render = template.compile(tpl);

    var table = $('#example1').DataTable({
        ajax: {
            url: "/admin/api/materialCategory"
        },
        "order": [[1, 'asc']],// dt默认是第一列升序排列 这里第一列为序号列，所以设置为不排序，并把默认的排序列设置到后面
        "serverSide": true,
        "fnServerData": function(sUrl, aoData, fnCallback){
            $.ajax({
                url:"/admin/api/materialCategory",
                dataType:"json",
                data:{"dataTableParam":JSON.stringify(aoData)},
                success:function(result){
                    fnCallback(result);
                }
            });
        },
        "sPaginationType": "full_numbers",
        "bPaginate": true,  //是否显示分页
        "columns": [
            {"data": null}, //因为要加行号，所以要多一列，不然会把第一列覆盖
            {"data": "name"},
            {"data": "profile"},
            {"data": "createTime"},
            {"data": "updateTime"},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [0.-1]
            },
            {
                "targets": 5,
                "render": function (data, type, row, meta) {
                    var context =
                    {
                        func: [
                            {"name": "修改", "fn": "edit(" + row.id + ")", "type": "primary", "id":"editRowBtn"},
                            {"name": "删除", "fn": "del(\'" + row.id + "\')", "type": "danger", "id":"delRowBtn"}
                        ]
                    };
                    var html = render(context);
                    return html;
                }
            },{
                "targets":[3,4],
                "render": function ( data, type, row, meta ) {
                    return format(data);
                }
            }

        ],
        "language": {
            "lengthMenu": "每页_MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "infoEmpty": "无记录",
            "search": "搜索：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "first":"首页",
                "last":"尾页",
                "previous": "上一页",
                "next": "下一页"
            }
        },
        "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +
        "t" +
        "<'row'<'col-xs-6'i><'col-xs-6'p>>"

    });

    //多选
    $('#example1 tbody').on('click', 'tr', function () {
        $(this).toggleClass('selected');
    });

    //添加序号
    //不管是排序，还是分页，还是搜索最后都会重画，这里监听draw事件即可
    table.on('draw.dt',function() {
        table.column(0, {
            search: 'applied',
            order: 'applied'
        }).nodes().each(function(cell, i) {
            //i 从0开始，所以这里先加1
            i = i+1;
            //服务器模式下获取分页信息
            var page = table.page.info();
            //当前第几页，从0开始
            var pageno = page.page;
            //每页数据
            var length = page.length;
            //行号等于 页数*每页数据长度+行号
            var columnIndex = (i+pageno*length);
            cell.innerHTML = columnIndex;
        });
    }).draw();

});
