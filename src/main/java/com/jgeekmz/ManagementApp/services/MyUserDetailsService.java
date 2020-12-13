package com.jgeekmz.ManagementApp.services;

import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.models.UserPrincipal;
import com.jgeekmz.ManagementApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = Optional.ofNullable(this.userRepository.findByUsername(username));
        org.springframework.security.core.userdetails.User springUser = null;

        //Optional<User> user  = userRepository.findByUsername(username);

        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("User not found!" + username);
        } else {
            User user = opt.get();

            System.out.println("VLIZAM TUK!");
            List<String> roles = Collections.singletonList(user.getRoles());
            Set<GrantedAuthority> ga = new HashSet<>();
            for (String role : roles) {
                ga.add(new SimpleGrantedAuthority(role));
            }

            springUser = new org.springframework.security.core.userdetails.User(username, user.getPassword(), ga);
        }


        //return user.map(UserPrincipal::new).get();
        //return opt.map(UserPrincipal::new).get();
        return springUser;
    }

}

