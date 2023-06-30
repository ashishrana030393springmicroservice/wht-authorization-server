package com.user.auth.dao;

import com.user.auth.entity.user.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission,Long> {
    Permission findByPermissionId(String id);
}
