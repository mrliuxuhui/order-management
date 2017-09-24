/**
 * Created by 刘旭辉 on 2017/9/22.
 */

$(function(){

    //

    //modal
    $("#modal-add, #modal-update").on("hidden.bs.modal", function() {
        $(this).removeData("hidden.bs.modal");
        $("#data-form")[0].reset();
        $("#data-form-update")[0].reset();
    });

    template.helper("formatNumber",function(value, points){
        if(value){
            return value.toFixed(points);
        }else {
            return "";
        }

    });

    // 添加菜品
    $("#modal-add").on("click","button#submitBtn",function(){
        var dataForm = $("#data-form");
        var orderId = $('input[name="orderId"]').val();
        $.ajax({
            url:"/admin/api/order/"+orderId+"/detail",
            type:"POST",
            data:JSON.stringify(form.serializeJSON()),
            success:function(result){
                $("#modal-add").modal("hide");
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

    $("#example1 table tbody").on("click","#btn-update-order",function(){
        var data = table.row($(this).parents("tr")).data();
        $("#modal-update input#detailId").val(data.id);
        $("#modal-update input#dish").val(data.menu.name);
        $("#modal-update input#mount").val(data.mount);
        $("#modal-update input#price").val(data.nenu.price.toFixed(2));
        $("#modal-update input#unit").val(data.measurement.cname);
        var total = data.deliverMount*data.menu.price;
        $("#modal-update input#total").val(total.toFixed(2));
        $("#modal-update input#mount").on("change",function(){
            var total = data.deliverMount*data.menu.price;
            $("#modal-update input#total").val(total.toFixed(2));
        });
        $("#modal-update").modal("show");
    });
    $("#modal-update").on("click","button#updateMountBtn",function(){
        var detailId = $("#modal-update input#detailId").val();
        //get order detail
        $.ajax({
            url:"/order/detail/"+detailId,
            dataType:"json",
            data:{"mount":$("#modal-update input#mount").val()},
            success:function(result){
                $("modal-update").modal("hide");
            }

        });
    });

    var render = template.compile($("#detail-order-template").text());

    //select2
    $("select.order-search-by-table").select2({
        ajax: {
            url: "/admin/api/order/numbers",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    tableNo: params.term,
                };
            },
            processResults: function (data) {
                return {
                    results: data
                };
            },
            cache: true
        },
        placeholder: '输入桌号查单..',
        escapeMarkup: function (markup) { return markup; },
        minimumInputLength: 1
    });

    template.helper("formatNumber",function(value, points){
        if(value){
            return value.toFixed(points);
        }else {
            return "";
        }

    });
    template.helper("formatStatus",function(status){
        if(3 == status){
            return "已上菜";
        }else{
            return "";
        }
    });
    var render = template.compile($("#order-detail-list").text());
    //
    $("select.order-search-by-table").on("select2:select",function(){
        var selected = $("select.order-search-by-table").select2("data");
        if(selected){
            $("h4#order-number").text(selected.number);
            $("h4#table-number").text(selected.tableNo);
            $("h4#order-time").text(format(selected.createTime));
            $("h4#total-price").text(selected.totalPrice.toFixed(2));

            $.ajax({
                url:"/admin/api/order/"+selected.id,
                type:"get",
                dataType:"json",
                success:function(result){

                    if(result&&result.list){
                        var html = render.render(result.list);
                        $("#example1 tbody").append(html);
                    }
                }

            });
        }
    });

    // menu select 2
    $("select#menuId").select2({
        ajax: {
            url: "/admin/api/menu/search",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    q: params.term,
                };
            },
            processResults: function (data) {
                return {
                    results: data
                };
            },
            cache: true
        },
        placeholder: '输入菜名..',
        escapeMarkup: function (markup) { return markup; },
        minimumInputLength: 1
    });

});
