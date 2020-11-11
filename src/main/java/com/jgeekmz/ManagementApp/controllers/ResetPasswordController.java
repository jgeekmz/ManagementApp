package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResetPasswordController {
    private final Logger log = LoggerFactory.getLogger(ResetPasswordController.class);

    @RequestMapping(value="/resetPassword", method = RequestMethod.GET)
    public ModelAndView resetPass(ModelAndView modelAndView, User user) {
        log.info("Reset Password Page");
        modelAndView.addObject("user", user);
        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }


}