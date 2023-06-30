package com.user.auth.dao;

import com.user.auth.entity.client.AuthenticationMethod;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthenticationMethodRepository extends CrudRepository<AuthenticationMethod,Long> {
    @Query("Select method from AuthenticationMethod method LEFT JOIN Fetch clients where method.authenticationMethod =:method")
    Optional<AuthenticationMethod> findByAuthenticationMethod(@Param("method") String method);
}
