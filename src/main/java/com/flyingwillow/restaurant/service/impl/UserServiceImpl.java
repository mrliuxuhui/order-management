package com.flyingwillow.restaurant.service.impl;

import com.flyingwillow.restaurant.domain.Role;
import com.flyingwillow.restaurant.domain.User;
import com.flyingwillow.restaurant.mapper.RoleMapper;
import com.flyingwillow.restaurant.mapper.UserMapper;
import com.flyingwillow.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Permission;
import java.util.List;

/**
 * Created by liuxuhui on 2017/9/14.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public List<Role> getRolesByUsername(String username) {
        return userMapper.getRolesByUsername(username);
    }

    @Override
    public List<String> getPermissionsByRole(Integer roleId) {
        return roleMapper.getPermissionsByRole(roleId);
    }
}
