package com.flyingwillow.restaurant.spring.response;

import com.flyingwillow.restaurant.util.web.AjaxUtils;
import com.flyingwillow.restaurant.util.web.WebUtil;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/14.
 */
public class ExceptionResponse {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Exception exception;

    private HashMap<Class,ExceptionInfo> configMap;

    private ExceptionInfo unknown;


    public ExceptionResponse(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        this.request = request;
        this.response = response;
        this.exception = exception;
        initConfig();
    }

    private void initConfig(){
        configMap = new HashMap<>();
        configMap.put(IncorrectCredentialsException.class,new ExceptionInfo().setStatusCode(403).setMsg("用户名或密码错误").setException(exception));
        configMap.put(ExcessiveAttemptsException.class,new ExceptionInfo().setStatusCode(403).setMsg("登录失败次数过多").setException(exception));
        configMap.put(LockedAccountException.class,new ExceptionInfo().setStatusCode(403).setMsg("帐号已被锁定").setException(exception));
        configMap.put(DisabledAccountException.class,new ExceptionInfo().setStatusCode(403).setMsg("帐号已被禁用").setException(exception));
        configMap.put(ExpiredCredentialsException.class,new ExceptionInfo().setStatusCode(403).setMsg("帐号已过期").setException(exception));
        configMap.put(UnknownAccountException.class,new ExceptionInfo().setStatusCode(403).setMsg("帐号不存在").setException(exception));
        configMap.put(UnauthorizedException.class,new ExceptionInfo().setStatusCode(403).setMsg("您没有得到相应的授权").setException(exception));

        unknown = new ExceptionInfo().setStatusCode(500).setMsg("未知错误");
    }

    public void writeResponse(){

        ExceptionInfo info = configMap.get(exception.getClass());
        if(null==info){//
            for(Map.Entry<Class,ExceptionInfo> entry : configMap.entrySet()){
                if(exception.getClass().isAssignableFrom(entry.getKey())){
                    info = entry.getValue();
                    break;
                }
            }
        }

        if(null==info){
            info = unknown;
        }

        if(AjaxUtils.isAjaxRequest(request)){
            WebUtil.writeJsonResponse(response,info.getStatusCode(),info.toJsonString());
        }else {
            WebUtil.sendRedirect(response,info.getView());
        }

    }
}
