package com.flyingwillow.restaurant.util.web;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 刘旭辉 on 2017/9/22.
 */
public class SerialNumberGenerator {

    public static String getSerialNumber(String prefix, int number){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.isNotBlank(prefix)?prefix.toUpperCase():"");
        sb.append(sdf.format(new Date()));
        sb.append(String.format("%03d",number));
        return sb.toString();
    }
}
