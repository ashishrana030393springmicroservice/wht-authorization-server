package com.user.auth.dao;

import com.user.auth.entity.client.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ClientRepository extends CrudRepository<Client,Long> {
    @Query("Select c from Client c LEFT JOIN FETCH authenticationMethods LEFT JOIN FETCH redirectUrls LEFT JOIN FETCH scopes LEFT JOIN FETCH grants where c.clientId =:clientId")
    Client findByClientId(@Param("clientId") String clientId);
}
