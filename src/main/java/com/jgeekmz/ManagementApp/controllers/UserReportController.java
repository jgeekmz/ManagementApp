package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.security.ActiveUserStore;
import com.jgeekmz.ManagementApp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class UserReportController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    //Track of logged users
    @Autowired
    ActiveUserStore activeUserStore;

    /*@RequestMapping(value="/usersReports", method = RequestMethod.GET)
    public ModelAndView showUserReports (ModelAndView modelAndView, User user, Locale locale) {

        //When the user was registered, on which date
        //List<User> userList = userService.findAll();

        modelAndView.addObject("users", activeUserStore.getUsers());
        modelAndView.setViewName("usersReport");
        return modelAndView;
    }*/

    @GetMapping("/usersReports")
    public String getLoggedUsers(Locale locale, Model model) {
        //log.info("Enters the logged users page!");
        //log.info("Logged in user>>>>>" + activeUserStore.getUsers());
        model.addAttribute("users", activeUserStore.getUsers());
        model.addAttribute("msg", "Welcome to the Admin Report Page!");
        return "usersReport";
    }


}
