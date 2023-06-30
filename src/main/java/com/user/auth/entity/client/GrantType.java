package com.user.auth.entity.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="grants")
public class GrantType extends BaseId{
    @Column(unique = true)
    private String grantType;
    @ManyToMany
    @JoinTable(name = "clients_grants", joinColumns = {@JoinColumn(name = "grant_id")}, inverseJoinColumns = {@JoinColumn(name="client_id")})
    private Set<Client> clients = new HashSet<>();
}
