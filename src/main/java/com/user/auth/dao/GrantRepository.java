package com.user.auth.dao;

import com.user.auth.entity.client.GrantType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GrantRepository extends CrudRepository<GrantType,Long> {
    @Query("Select g from GrantType g LEFT JOIN FETCH clients where g.grantType=:grant")
    Optional<GrantType> findByGrantType(@Param("grant") String grant);
}
