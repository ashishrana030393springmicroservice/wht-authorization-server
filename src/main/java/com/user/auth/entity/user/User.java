package com.user.auth.entity.user;

import com.user.auth.entity.client.BaseId;
import com.user.auth.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User extends BaseId {
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    private Gender gender;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(unique = true,nullable = false)
    private String userId;
    @Column(nullable = false)
    private String password;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean enabled;
    @ManyToMany(mappedBy = "users")
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        role.getUsers().add(this);
        roles.add(role);
    }

    public void removeRole(Role role){
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public static User.Builder withId(String id){
        return new User.Builder(id);
    }

    public static class Builder{
        private String username;
        private String email;
        private String userId;
        private String password;
        private boolean enabled;
        private Gender gender;
        private String firstName;
        private String lastName;

        Builder(String id){
            this.userId = id;
        }

        public User.Builder username(String username){
            this.username = username;
            return this;
        }
        public User.Builder email(String email){
            this.email = email;
            return this;
        }

        public User.Builder password(String encryptedPassword){
            this.password = encryptedPassword;
            return this;
        }
        public User.Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public User.Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public User.Builder enabled(boolean enabled){
            this.enabled = enabled;
            return this;
        }

        public User.Builder gender(Gender gender){
            this.gender = gender;
            return this;
        }

        public User build(){
            User user = new User();
            user.setPassword(password);
            user.setUserId(userId);
            user.setEnabled(enabled);
            user.setUsername(username);
            user.setEmail(email);
            user.setGender(gender);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            return user;
        }

    }
}
