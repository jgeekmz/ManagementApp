package com.jgeekmz.ManagementApp.services;

import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.models.UserPrincipal;
import com.jgeekmz.ManagementApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        //org.springframework.security.core.userdetails.User springUser = null;

        if (user == null) {
            throw new UsernameNotFoundException("User not found!" + username);
        }/* else {
            User user = opt.get();

            *//*List<Set<Role>> roles = Collections.singletonList(user.getRoles());
            Set<GrantedAuthority> ga = new HashSet<>();
            for (Set<Role> role : roles) {
                ga.add(new SimpleGrantedAuthority(role));
            }*//*
            springUser = new org.springframework.security.core.userdetails.User(username, user.getPassword());
        }*/

        //return user.map(UserPrincipal::new).get();
        //return opt.map(UserPrincipal::new).get();
        return new UserPrincipal(user);
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}

