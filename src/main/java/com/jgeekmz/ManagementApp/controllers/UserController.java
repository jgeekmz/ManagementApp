package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.models.User;
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
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final String REDIRECT = "redirect:/users";
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired private BCryptPasswordEncoder encoder;

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
    public Optional<User> findById(Integer id) {
        System.out.println(userRepository.findById(id));
        return userService.findById(id);
    }

    /*// REGISTER new user from Register Page
    // Only users can log into the system, a.k.a. some employees might have also username and password
    @RequestMapping(value = "users/addNew", method = RequestMethod.POST)
    public ModelAndView addNew(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redir, Model model) {
        RedirectView redirectView = new RedirectView("/login", true);
        RedirectView redirectViewTwo = new RedirectView("/register", true);
        RedirectView redd = new RedirectView("/register");

        if(result.hasErrors()) {
            ModelAndView mav = new ModelAndView("register","user",result.getAllErrors());
            mav.addObject("user",user);
            System.out.println("ERROR: " + result.hasFieldErrors("firstName"));
            System.out.println("ERROR: " + result.getFieldError("firstName"));
            log.info("Firstname is empty!");
            return mav;
        }

        String uname = user.getUsername();
        User usr = repoUser.findByUsername(uname);

        // Logging info for new reg users
        log.info(">> New registered: {}", user.toString());

        redir.addFlashAttribute("message", "You are successfully registered! Please login into your account!");
        redir.addFlashAttribute("messageExist", "User already exist!");
        //System.out.println("Results:" + bindingResult);

            // when the user is not registered in the database
            // we check the username at registration
            if (usr == null) {
                log.error("User don't exist! Username is available!!!");
                userService.saveUser(user);

                //
                // sent email notification to admin for the newly registered user to the platform
                try {
                    notificationService.sentNotification(user);
                } catch (MailException e) {
                    log.info("Error sending the notification" + e.getMessage());
                }

                ModelAndView mv= new ModelAndView("redirect:/login");
                mv.addObject("user",user);
                return mv;
            } else {
                log.error("User exist! Please use different username.");
            }
        //return new ModelAndView("register");
        return new ModelAndView("redirect:/register");
    }*/

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
        userService.save(user);
        return REDIRECT;
    }

    //Update user
    @RequestMapping(value = "users/update", method = { RequestMethod.PUT, RequestMethod.GET })
    public String update(User user) {
        userService.saveUser(user);
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

        userService.save(user);
        return REDIRECT;
    }

    //Return all not active users
    @GetMapping("/activations")
    public String activate(Model model, Boolean enabled, Principal principal){
       //Enabled == false
        System.out.println("Activations!");
        log.info("Logged in user >>>>>" + principal.getName());
        model.addAttribute("users",userService.findByValidFalse(enabled));
        return "activation";
    }

/*    @RequestMapping(value="/users/activate/{id}",method = {RequestMethod.PUT,RequestMethod.GET})
    //@ResponseBody
    public String activateUser(Integer id) {
        Optional<User> user = userService.findById(id);
        log.info("User found >>>" + user);
        userService.save(user);
        return "activation";
    }*/

}