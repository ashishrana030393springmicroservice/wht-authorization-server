package com.user.auth.entity.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientTokenSettings extends BaseId{
    @Column(name="access_token_ttl")
    private long accessTokenTTL;
    private String type;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
