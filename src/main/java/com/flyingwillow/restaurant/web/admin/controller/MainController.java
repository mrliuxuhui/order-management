package com.flyingwillow.restaurant.web.admin.controller;

import com.flyingwillow.restaurant.domain.Role;
import com.flyingwillow.restaurant.service.IUserService;
import com.flyingwillow.restaurant.util.web.Constants;
import com.flyingwillow.restaurant.web.admin.dto.UserDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/15.
 */
@Controller
public class MainController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(UserDTO userVO){

        UsernamePasswordToken token = userVO.token();

        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ModelAndView("login");
        }

        //登陆成功， 选择角色

        List<Role> roles = userService.getRolesByUsername((String) SecurityUtils.getSubject().getPrincipal());
        Session session = SecurityUtils.getSubject().getSession();
        if(null==roles||roles.isEmpty()){
            session.setAttribute(Constants.USER_CURRENT_ROLE_KEY,"user");
            return new ModelAndView("/layout/home/main");
        }else{
            Map<String,Object> model = new HashMap<>();
            model.put("roles",roles);
            return new ModelAndView("select_role", model);
        }

    }

    @RequestMapping(value = "/user/role/selection", method = RequestMethod.GET)
    public String selectRole(@RequestParam(required = true) String role, @RequestParam(required = false) String client){

        role = StringUtils.isBlank(role)?"user":role;

        if(SecurityUtils.getSubject().hasRole(role)){
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(Constants.USER_CURRENT_ROLE_KEY,role);
        }else{
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(Constants.USER_CURRENT_ROLE_KEY,"user");
        }

        return "/layout/home/main";

    }

}
