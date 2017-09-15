package com.flyingwillow.restaurant.web.admin.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liuxuhui on 2017/9/14.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String goPage(@PathVariable String page){

        if(StringUtils.isNotBlank(page)){
            return page;
        }
        return "blank";
    }
}
