package com.jgeekmz.ManagementApp.services;

import com.jgeekmz.ManagementApp.exceptions.UserNotFoundException;
import com.jgeekmz.ManagementApp.models.Role;
import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.repositories.RoleRepository;
import com.jgeekmz.ManagementApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
    }


    //Get All Users
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //Find roles
    public List<Role> findAllRoles(Integer userId) {
        return roleRepository.findAll();
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

    public void updateUser(User user) {
        userRepository.save(user);
    }

    // Add User from the user webpage
    public void addUser(User user) {
        userRepository.save(user);
    }

    // Get user by username
    public User findByUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    public User findByNames (String firstName, String lastName) {
        return (User) userRepository.findByFirstnameAndLastname(firstName,lastName);
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


    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find user with this email address" + email);
        }
    }

    //Find user by reset token
    public User get(String resetPasswordToken) {
        return userRepository.findByResetPasswordToken(resetPasswordToken);
    }

    // update password from reset link
    public void updatePassword(User user, String newPassword) {
    BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(newPassword);
    user.setPassword(encodedPassword);
    user.setResetPasswordToken(null);
    userRepository.save(user);
    }
}