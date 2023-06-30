package com.user.auth.service.impl.user;

import com.user.auth.dao.RoleRepository;
import com.user.auth.entity.user.Permission;
import com.user.auth.entity.user.Role;
import com.user.auth.service.PermissionService;
import com.user.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionService permissionService;

    @Override
    public Role create(String roleName){
        Role role = new Role();
        role.setRole(roleName);
        role.setRoleId(UUID.randomUUID().toString());
        return this.roleRepository.save(role);
    }

    @Override
    public Role addPermission(String roleId,String permissionId){
        Permission permission = permissionService.findByPermissionId(permissionId);
        Role role = roleRepository.findByRoleId(roleId);
        role.addPermission(permission);
        return this.roleRepository.save(role);
    }

    @Override
    public Role findRoleById(String roleId){
        return this.roleRepository.findByRoleId(roleId);
    }


}
