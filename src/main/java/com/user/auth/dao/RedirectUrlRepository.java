package com.user.auth.dao;

import com.user.auth.entity.client.RedirectUrl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RedirectUrlRepository extends CrudRepository<RedirectUrl,Long> {
    @Query("Select r from RedirectUrl r LEFT JOIN FETCH clients where r.url=:url")
    Optional<RedirectUrl> findByUrl(@Param("url") String url);
}
