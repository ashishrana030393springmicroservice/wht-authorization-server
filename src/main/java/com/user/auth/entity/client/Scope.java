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
@Table(name="scopes")
public class Scope extends BaseId{
    @Column(unique = true)
    private String scope;
    @ManyToMany
    @JoinTable(name = "clients_scopes", joinColumns = {@JoinColumn(name="scope_id")},inverseJoinColumns = {@JoinColumn(name="client_id")})
    private Set<Client> clients = new HashSet<>();
}
