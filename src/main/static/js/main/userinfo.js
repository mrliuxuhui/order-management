/**
 * Created by liuxuhui on 2017/9/19.
 */
$(function(){

    //
    $.ajax({
        url:"/user/api/detail",
        type:"get",
        dataType:"json",
        success:function(result){

            if(null!=result){

                //username

                // header
                $("li.user-menu > a > span.hidden-xs").text(result.name);
                $("li.user-menu > ul.dropdown-menu > li.user-header > p").html(result.name+'<small>'+result.role+'</small>');

                //left menu
                $("div.user-panel > div.info > p").text(result.name);

                // img
                if(result.img){
                    $("div.user-panel > div.image > img").attr("src",result.img);

                    //header
                    $("li.user-menu > a > img.user-image").attr("src",result.img);
                    $("li.user-menu > ul.dropdown-menu > li.user-header > img.img-circle").attr("src",result.img);
                }

            }

        }

    });

});
