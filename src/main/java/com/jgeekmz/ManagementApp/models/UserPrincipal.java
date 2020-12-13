package com.jgeekmz.ManagementApp.models;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private final String username;
    private String firstname;
    private String lastname;
    private String email;
    private final String password;
    private boolean enabled;
    private boolean banned;
    private String confirmationToken;
    private List<GrantedAuthority> authorties;
    private final User user;

    public UserPrincipal(User user) {
        this.username=user.getUsername();
        this.firstname=user.getFirstname();
        this.lastname=user.getLastname();
        this.password=user.getPassword();
        this.email=user.getEmail();
        this.banned=user.isBanned();
        this.enabled=user.isEnabled();
        this.user = user;
    }

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorties;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}