package com.user.auth.entity.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="authentication_methods")
public class AuthenticationMethod extends BaseId {
    @Column(unique = true)
    private String authenticationMethod;
    @ManyToMany()
    @JoinTable(name = "clients_authentication_methods", joinColumns = {@JoinColumn(name="authentication_method_id")},inverseJoinColumns = {@JoinColumn(name="client_id")})
    private Set<Client> clients = new HashSet<>();
}
