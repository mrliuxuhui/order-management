package com.flyingwillow.restaurant.spring.exception;

import com.flyingwillow.restaurant.spring.response.ExceptionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuxuhui on 2017/9/5.
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(httpServletRequest,httpServletResponse,e);
        exceptionResponse.writeResponse();
        return null;
    }
}
