package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.models.Employee;
import com.jgeekmz.ManagementApp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class ProfileController {
   @Autowired
    private EmployeeService employeeService;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal, Employee employee) {
        String un = principal.getName();
        System.out.println("UserPrinciple name : " + un);

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();

        Employee user = employeeService.findByUsername(userName);

        String firstName = user.getFirstname();
        String lastName = user.getLastname();
        String email = user.getEmail();
        String dateOfBirth = user.getDateOfBirth();
        String k = user.getMobile();
        String t = user.getAddress();
        String g = user.getCountry().getCapital();
        String p = user.getHireDate();

        System.out.println(dateOfBirth + " " + k + " " + g + " "+ email + " " + p);

        model.addAttribute("userName", userName);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("dateOfBirth", dateOfBirth);
        model.addAttribute("k", k);
        model.addAttribute("t", t);
        model.addAttribute("g", g);
        model.addAttribute("p", p);

        return "profile";
    }

    @RequestMapping( value= "/profile/editDetails", method = RequestMethod.POST)
    public String profileEdit (Model model, Employee employee) {

        System.out.println("PROFILE  EDIT Page 2021 :) :) :) :)");

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();

        return "redirect:/profile";
    }
}