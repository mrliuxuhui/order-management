package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.Role;
import com.flyingwillow.restaurant.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public interface UserMapper {

    public List<User> getUserList(Map<String, Object> params);

    public User getUserByUsername(String username);

    public List<Role> getRolesByUsername(String username);

    public void saveUser(User user);

    public void updateUser(User user);

    public void deleteUser(String username);
}
