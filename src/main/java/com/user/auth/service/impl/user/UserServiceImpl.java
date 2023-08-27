package com.user.auth.service.impl.user;

import com.user.auth.dao.UserRepository;
import com.user.auth.entity.user.Role;
import com.user.auth.entity.user.User;
import com.user.auth.enums.Gender;
import com.user.auth.model.UserRegistration;
import com.user.auth.service.RoleService;
import com.user.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public User create(UserRegistration userRegistration){
        User user = User.withId(UUID.randomUUID().toString())
                .email(userRegistration.getUsername())
                .username(userRegistration.getUsername().split("@")[0])
                .firstName(userRegistration.getFirstName())
                .lastName(userRegistration.getLastName())
                .password(passwordEncoder.encode(userRegistration.getPassword()))
                .enabled(true)
                .gender(Gender.valueOf(userRegistration.getGender()))
                .build();
        return this.userRepository.save(user);
    }

    @Override
    public User addRole(String userId, String roleId){
        Role role = this.roleService.findRoleById(roleId);
        User user = this.userRepository.findByUserId(userId);
        user.addRole(role);
        return this.userRepository.save(user);
    }

    @Override
    public User findByUsernameOrEmail(String identifier){
        return this.userRepository.findByUsernameOrEmail(identifier);
    }

    @Override
    public boolean checkIfUserAlreadyExist(String identifier) {
        return this.userRepository.checkUserExistByUsernameOrEmail(identifier);
    }
}
