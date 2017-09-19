package com.flyingwillow.restaurant.web.admin.controller;

import com.flyingwillow.restaurant.domain.User;
import com.flyingwillow.restaurant.service.IUserService;
import com.flyingwillow.restaurant.util.web.WebUtil;
import com.flyingwillow.restaurant.web.admin.vo.UserVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuxuhui on 2017/9/19.
 */
@RestController
@RequestMapping("/user/api")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<UserVO> getUserDetail(){

        String username = (String) SecurityUtils.getSubject().getPrincipal();

        User user = userService.getUserByUsername(username);

        if(null==user){
            return new ResponseEntity<UserVO>(HttpStatus.NOT_FOUND);
        }

        UserVO userVO = new UserVO(user);
        return new ResponseEntity<UserVO>(userVO, WebUtil.getUTF8Header(), HttpStatus.OK);
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserMenu(){

        return new ResponseEntity<Object>(null,WebUtil.getUTF8Header(),HttpStatus.OK);
    }
}
