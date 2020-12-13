package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserReportController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/usersReports", method = RequestMethod.GET)
    public ModelAndView showUserReports (ModelAndView modelAndView, User user) {

        //When the user was registered, on which date
        //List<User> userList = userService.findAll();

        modelAndView.addObject("user", user);
        modelAndView.setViewName("usersReport");
        return modelAndView;
    }
}
