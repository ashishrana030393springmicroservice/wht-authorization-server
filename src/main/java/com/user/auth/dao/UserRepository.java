package com.user.auth.dao;

import com.user.auth.entity.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long> {
    public User findByUserId(String id);
    @Query("Select u from User u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.permissions p where u.username =:identifier or u.email =:identifier")
    User findByUsernameOrEmail(@Param("identifier") String identifier);

    @Query("Select case WHEN count(u.id) > 0 THEN true ELSE false END as exist from User u where u.username =:identifier or u.email =:identifier")
    boolean checkUserExistByUsernameOrEmail(@Param("identifier") String identifier);
}
