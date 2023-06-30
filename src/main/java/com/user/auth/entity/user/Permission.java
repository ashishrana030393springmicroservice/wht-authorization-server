package com.user.auth.entity.user;

import com.user.auth.entity.client.BaseId;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Permission extends BaseId {
    @Column(unique = true,nullable = false)
    private String permissionId;
    @Column(unique = true,nullable = false)
    private String permission;
    @ManyToMany
    @JoinTable(name="roles_permissions",joinColumns = {@JoinColumn(name = "permission_id")},inverseJoinColumns = {@JoinColumn(name="role_id")})
    private Set<Role> roles = new HashSet<>();
}
