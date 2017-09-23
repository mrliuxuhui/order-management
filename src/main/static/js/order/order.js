/**
 * Created by 刘旭辉 on 2017/9/22.
 */

$(function(){

    // iCheck
    $('input[type="checkbox"].flat-red').iCheck({
        checkboxClass: 'icheckbox_flat-green'
    });
    $('input[type="checkbox"].flat-red').on("click",function(){
        table.ajax.reload();
    });
    //
    $("#modal-check h6.box-title a").on("click",function(){
        $(this).find("i.fa").toggleClass("fa-chevron-down fa-chevron-up");
    });

    //modal
    $("#modal-check").on("hidden.bs.modal", function() {
        $(this).removeData("hidden.bs.modal");
        $("#checkOutForm")[0].reset();
    });

    template.helper("formatNumber",function(value, points){
        if(value){
            return value.toFixed(points);
        }else {
            return "";
        }

    });
    var render = template.compile($("#detail-order-template").text());

    // 结算
    $("#modal-check button#submitBtn").on("click",function(){
        var form = $("form#checkOutForm");
        var orderId = $('input[name="orderId"]').val();
        $.ajax({
            url:"/admin/api/order/"+orderId+"/checkout",
            type:"POST",
            data:JSON.stringify(form.serializeJSON()),
            success:function(result){
                $("#modal-check").modal("hide");
            }
        });
    });

    $('input[name="receivables"], input[name="receipts"]').on("blur",function(){
        var reb =  $('input[name="receivables"]').val();
        var rep = $('input[name="receipts"]').val();
        if(reb&&rep&&rep>reb){
            var changes = rep-reb;
            $('input[name="changes"]').val(changes.toFixed(2));
        }
    });

    $("#example1 table tbody").on("click","#btn-check-order",function(){
        var data = table.row($(this).parents("tr")).data();
        $("#order-number").text(data.number);
        $("#table-number").text(data.tableNo);
        $("input#orderId").val(data.id);
        //get order detail
        $.ajax({
            url:"/admin/api/order/"+data.id,
            dataType:"json",
            success:function(result){
                if(result){
                    var html = render(result);
                    $("#modal-check .modal-body table tbody").append(html);

                    var total = 0;
                    if(result.list){
                        $.each(result.list,function(index,item){
                            total += item.priceSum?item.priceSum:0;
                        });

                        //应付。
                        $('input[name="receivables"]').val(total.toFixed(2));
                    }

                    $("modal-check").modal("show");
                }
            }

        });
    });

    // data tables
    var table = $('#example1').DataTable({
        ajax: {
            url: "/admin/api/order"
        },
        "ordering": false,// dt默认是第一列升序排列 这里第一列为序号列，所以设置为不排序，并把默认的排序列设置到后面
        "serverSide": true,
        "fnServerData": function(sUrl, aoData, fnCallback){
            $.ajax({
                url:"/admin/api/order",
                dataType:"json",
                data:{"dataTableParam":JSON.stringify(aoData),"showAll":$('.box-header label>input.flat-red[type="checkbox"]').is("checked")},
                success:function(result){
                    fnCallback(result);
                }
            });
        },
        "sPaginationType": "full_numbers",
        "bPaginate": true,  //是否显示分页
        "columns": [
            {"data": null}, //因为要加行号，所以要多一列，不然会把第一列覆盖
            {"data": "number"},
            {"data": "tableNo"},
            {"data": "createTime"},
            {"data": "totalPrice"},
            {"data": "pushed"},
            {"data": "rush"},
            {"data": null},
            {"data": null}
        ],
        "columnDefs": [
            {
                "searchable": false,
                "orderable": false,
                "targets": [0.-1]
            },
            {
                "targets": 8,
                "render": function (data, type, row, meta) {
                    return '<button id="btn-delete-order" class="btn btn-block btn-danger">删除</button>';
                }
            },{
                "targets": 7,
                "render": function (data, type, row, meta) {
                    return '<button id="btn-check-order" class="btn btn-block btn-danger">结算</button>';
                }
            },{
                "targets":[5,6],
                "render": function ( data, type, row, meta ) {
                    return data?"是":"否";
                }
            },{
                "targets":4,
                "render":function ( data, type, row, meta ) {
                    return row.totalPrice?row.totalPrice.toFixed(2):"";
                }
            },{
                "targets":3,
                "render":function ( data, type, row, meta ) {
                    return row.createTime?format(row.createTime):"";
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
