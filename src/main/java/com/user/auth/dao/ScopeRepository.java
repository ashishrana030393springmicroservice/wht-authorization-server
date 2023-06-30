package com.user.auth.dao;

import com.user.auth.entity.client.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScopeRepository extends CrudRepository<Scope,Long> {
    @Query("Select s from Scope s LEFT JOIN FETCH clients where s.scope=:scope")
    Optional<Scope> findByScope(@Param("scope") String scope);
}
