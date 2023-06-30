package com.user.auth.service;

import com.user.auth.entity.user.Permission;

public interface PermissionService {
    Permission create(String name);

    Permission findByPermissionId(String permissionId);
}
