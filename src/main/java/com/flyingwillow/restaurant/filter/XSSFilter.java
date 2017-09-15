package com.flyingwillow.restaurant.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by liuxuhui on 2016/1/6.
 */
public class XSSFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        //XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        //xssRequest.setAttribute("dirs",getDirs());
        //servletRequest.setAttribute("dirs",getDirs());
        //filterChain.doFilter(xssRequest, servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    /*private Row getDirs(){
        Row row = new Row();
        Row configRow = Config.getInstance().getRow();
        for(Object key : configRow.keySet()){
            String strKey = (String) key;
            if(strKey.contains("_DIR")){
                row.put(key,configRow.get(key));
            }
        }

        row.put("URL_PREFIX",configRow.gets("URL_PREFIX",""));

        return row;
    }*/
}
