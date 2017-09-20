package com.flyingwillow.restaurant.web.admin.vo;

/**
 * Created by liuxuhui on 2017/9/20.
 */
public class JsonResponseStatus {

    private int status;
    private boolean success;
    private String msg;

    public JsonResponseStatus() {
    }

    public JsonResponseStatus(int status, boolean success, String msg) {
        this.status = status;
        this.success = success;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public JsonResponseStatus setStatus(int status) {
        this.status = status;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public JsonResponseStatus setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResponseStatus setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public static JsonResponseStatus buildSuccessResponse(){
        return new JsonResponseStatus(0,true,"ok");
    }

    public static JsonResponseStatus buildFailResponse(){
        return new JsonResponseStatus(500,false,"internal error");
    }

    public static JsonResponseStatus buildFailResponse(int status, String msg){
        return new JsonResponseStatus(status,false,msg);
    }
}
