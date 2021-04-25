package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.models.Role;
import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.repositories.RoleRepository;
import com.jgeekmz.ManagementApp.repositories.UserRepository;
import com.jgeekmz.ManagementApp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.MessageFormat;
import java.util.*;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final String REDIRECT = "redirect:/users";

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired private BCryptPasswordEncoder encoder;
    @Autowired private RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public String findUser (Principal principal) { return principal.getName(); }

    // Get All Users
    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "user";
    }

    //Find user by ID
    @RequestMapping("users/findById")
    @ResponseBody
    public Optional<User> findById(Integer id, Model model) {
        return userService.findById(id);
    }

    //@GetMapping("/users/checkUser")
    @RequestMapping(value = "/login/checkUser", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView checkUser(@ModelAttribute("user") User user, RedirectAttributes redir) {
        RedirectView redirectView = new RedirectView("/login", true);
        RedirectView redirectViewTwo = new RedirectView("/index", true);
        redir.addFlashAttribute("messageUserNotExist", "User is not being registered!");
        //redir.addFlashAttribute("messageUserExist", "User already exist!");

        return redirectView;
    }

    // Adding new user from user page
    @RequestMapping(value = "users/addNewUser", method = RequestMethod.POST)
    public String addNewUser(User user) {
        //save new user to db
        Date dt = new Date();
        user.setRegDate(dt);
        userService.save(user);
        return REDIRECT;
    }

    //Update user
    @RequestMapping(value = "users/update", method = { RequestMethod.PUT, RequestMethod.GET })
    public String update(User user) {
        userService.updateUser(user);
        return REDIRECT;
    }

    //Delete user
    @RequestMapping(value = "users/delete", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String delete(Integer id) {
        userService.delete(id);
        return REDIRECT;
    }

    //User password update
    @RequestMapping(value="users/updateUserPassword", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updatePassword(User user) {
        //method for updating the password with modal form admin portal
        // coke
        String v1 = "$2a$10$EXTfAbGlQ75pWs7E02gAGuWjMTYliIqpGqvLgMYhbvmaXlCvisb8a";
        // java
        String v2 = "$2a$10$qbis0aLF3pBjwGKhOIH9RuzJpK/37zoTCsPzO5pCh9Kl49NqatV0G";
        String v3 = "java";

        boolean result = encoder.matches(v3, v2);
        log.info("Result >>>> " + result);
        System.out.println(MessageFormat.format("PRINTING USER ROLE: {0} : {1}", user.getUsername(), user.getRoles().toString()));
        ArrayList<Role> rl = new ArrayList<>(user.getRoles());
        List<Role> list = new LinkedList<Role>((rl));
        System.out.println("LIST >>> " + list);
        user.setRoles(list);


        userService.save(user);
        return REDIRECT;
    }

    //Return all not active users
    @GetMapping("/activations")
    public String activate(Locale locale, Model model, Boolean enabled, Principal principal, RedirectAttributes redir) {
        //log.info("Logged in user >>>>>" + principal.getName());
        List<User> user = userService.findByValidFalse(enabled);

        if (user.isEmpty()) {
            redir.addFlashAttribute("msg", "No users for activation available!");
            return "redirect:/users";
        } else {
            model.addAttribute("users", userService.findByValidFalse(enabled));
        }
        //model.addAttribute("users",userService.findByValidFalse(enabled));
        return "activation";
    }
}