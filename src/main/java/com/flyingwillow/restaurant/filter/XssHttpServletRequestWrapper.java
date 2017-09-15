package com.flyingwillow.restaurant.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by liuxuhui on 2016/1/6.
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final static List<Pattern> filterList = new ArrayList<>();

    HttpServletRequest orgRequest = null;
    private XSSFilter filter;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
        try {
            filter = new XSSFilter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if(null==values){
            return values;
        }
        String[] new_values = new String[values.length];
        for(int i=0;i<values.length;i++){
            new_values[i] = xssEncode(values[i]);
        }
        return new_values;
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {

        String value = super.getHeader(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    /**
     * xss 过滤
     * */
    public String xssEncode(String content){

        if (content != null) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            content = content.replaceAll("", "");

            for(Pattern pattern : initFilters()){
                content = pattern.matcher(content).replaceAll("");
            }
        }
        return content;
    }

    /**
     * 获取最原始的request
     *
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }

        return req;
    }

    private static List<Pattern> initFilters(){
        if(filterList.isEmpty()){
            filterList.add(Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE));
            filterList.add(Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
            filterList.add(Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
            filterList.add(Pattern.compile("</script>", Pattern.CASE_INSENSITIVE));
            filterList.add(Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
            filterList.add(Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
            filterList.add(Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
            filterList.add(Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE));
            filterList.add(Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE));
            filterList.add(Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
        }
        return filterList;
    }
}
