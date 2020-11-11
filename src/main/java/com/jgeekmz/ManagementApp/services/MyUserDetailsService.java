package com.jgeekmz.ManagementApp.services;

import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.models.UserPrincipal;
import com.jgeekmz.ManagementApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> existingUser = this.userRepository.findByUsername(username);

        
        //Optional<User> user  = userRepository.findByUsername(username);

        if(existingUser==null) {
            throw new UsernameNotFoundException("User not found!");
        }

        //return user.map(UserPrincipal::new).get();
        return existingUser.map(UserPrincipal::new).get();
    }


}
