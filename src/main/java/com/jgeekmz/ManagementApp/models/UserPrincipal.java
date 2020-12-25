package com.jgeekmz.ManagementApp.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserPrincipal implements UserDetails {

    private User user;

    private final String username;
    private String firstname;
    private String lastname;
    private String email;
    private final String password;
    private boolean enabled;
    private boolean banned;
    private String confirmationToken;

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

    public String getFirstname() {
        return this.user.getFirstname();
    }
    public String getLastname() {
        return this.user.getLastname();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /*Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
            Set<GrantedAuthority> ga = new HashSet<>();
            for (String role : userRoles) {
                ga.add(new SimpleGrantedAuthority(role));
        }
        System.out.println(authorities);
        //return authorities;
        return ga;*/

       List<Role> roles = (List<Role>) user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        List < GrantedAuthority > Result = new ArrayList < GrantedAuthority > (authorities);

        return  Result;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.isEnabled();
    }
}