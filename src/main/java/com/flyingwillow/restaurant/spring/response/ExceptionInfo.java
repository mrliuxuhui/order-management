package com.flyingwillow.restaurant.spring.response;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/14.
 */
public class ExceptionInfo {

    private Integer statusCode;
    private String msg;
    private String view;
    private Exception exception;

    public Integer getStatusCode() {
        return statusCode;
    }

    public ExceptionInfo setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getMsg() {
        if(StringUtils.isNotBlank(msg)){
            return msg;
        }else if(null!=exception){
            return exception.getMessage();
        }
        return null;
    }

    public ExceptionInfo setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getView() {
        return view;
    }

    public ExceptionInfo setView(String view) {
        this.view = view;
        return this;
    }

    public Exception getException() {
        return exception;
    }

    public ExceptionInfo setException(Exception exception) {
        this.exception = exception;
        return this;
    }

    public String toJsonString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
            sb.append("\"success\":false").append(",");
            sb.append("\"statusCode\":").append(statusCode).append(",");
            sb.append("\"msg\":").append("\"").append(msg).append("\"");
        sb.append("}");
        return sb.toString();
    }
}
