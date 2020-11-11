package com.jgeekmz.ManagementApp.services;

import java.util.List;
import java.util.Optional;

import com.jgeekmz.ManagementApp.controllers.UserController;
import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    private UserRepository userRepository;

    @Autowired public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired private BCryptPasswordEncoder encoder;

    //Get All Users
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //Get User By Id
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    //Delete User
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    //Update User from application
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // Register User from registration page
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Add User from the user webpage
    public void addUser(User user) {
        userRepository.save(user);
    }

    // Get user by username
    public Optional<User> findByUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    //Find user by his username
    public boolean userExist(String username) {
        return findByUsername(username).isPresent();
    }

    //Find user by his email address
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //Find user bis his generated token
    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    //Find user by validation check
    public List<User> findByValidFalse(Boolean enabled) {
        return  userRepository.findByEnabledFalse(enabled);
    }

    public void activate (Integer id, Boolean enabled){
        userRepository.activateUser(id, enabled);
    }



}