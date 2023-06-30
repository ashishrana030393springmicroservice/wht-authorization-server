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
@Table(name="redirect_urls")
public class RedirectUrl extends BaseId {
    @Column(unique = true)
    private String url;
    @ManyToMany
    @JoinTable(name = "clients_redirect_urls", joinColumns = {@JoinColumn(name="redirect_url_id")},inverseJoinColumns = {@JoinColumn(name="client_id")})
    private Set<Client> clients = new HashSet<>();
}
