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

});
