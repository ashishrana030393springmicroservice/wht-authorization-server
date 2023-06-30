package com.user.auth.dao;

import com.user.auth.entity.user.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRoleId(String id);
}
