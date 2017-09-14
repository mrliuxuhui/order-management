package com.flyingwillow.restaurant.service;

import com.flyingwillow.restaurant.domain.Role;
import com.flyingwillow.restaurant.domain.User;

import java.util.List;

/**
 * Created by liuxuhui on 2017/9/14.
 */
public interface IUserService {

    public User getUserByUsername(String username);
    public List<Role> getRolesByUsername(String username);
    public List<String> getPermissionsByRole(Integer roleId);
}
