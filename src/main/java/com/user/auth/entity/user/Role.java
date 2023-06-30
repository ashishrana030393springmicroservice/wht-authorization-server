package com.user.auth.entity.user;

import com.user.auth.entity.client.BaseId;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Role extends BaseId {
    @Column(unique = true,nullable = false)
    private String roleId;
    @Column(unique = true,nullable =false)
    private String role;
    @ManyToMany(mappedBy = "roles")
    private Set<Permission> permissions = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name="role_id")}, inverseJoinColumns = {@JoinColumn(name="user_id")})
    private Set<User> users = new HashSet<>();

    public void addPermission(Permission permission){
        permissions.add(permission);
        permission.getRoles().add(this);
    }

    public void removePermission(Permission permission){
        permissions.remove(permission);
        permission.getRoles().remove(this);
    }
}
