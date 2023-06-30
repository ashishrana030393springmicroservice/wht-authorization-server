package com.user.auth.service.impl.user;

import com.user.auth.dao.PermissionRepository;
import com.user.auth.entity.user.Permission;
import com.user.auth.service.PermissionService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Data
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public Permission create(String name){
        Permission permission = new Permission();
        permission.setPermission(name);
        permission.setPermissionId(UUID.randomUUID().toString());
        return this.permissionRepository.save(permission);
    }

    @Override
    public Permission findByPermissionId(String permissionId){
        return this.permissionRepository.findByPermissionId(permissionId);
    }
}
