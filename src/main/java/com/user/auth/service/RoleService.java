package com.user.auth.service;

import com.user.auth.entity.user.Role;

public interface RoleService {
    Role create(String roleName);

    Role addPermission(String roleId, String permissionId);

    Role findRoleById(String roleId);
}
