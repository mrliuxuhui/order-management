package com.flyingwillow.restaurant.mapper;

import com.flyingwillow.restaurant.domain.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxuhui on 2017/9/7.
 */
public interface RoleMapper {

    public List<Role> getRoleList(Map<String, Object> params);

    public List<String> getPermissionsByRole(Integer roleId);

    public void saveRole(Role role);

    public void updateRole(Role role);

    public void deleteRole(Integer id);
}
