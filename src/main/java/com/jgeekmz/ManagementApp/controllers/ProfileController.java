package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.models.Employee;
import com.jgeekmz.ManagementApp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        //String un = principal.getName();
        //System.out.println(un);

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();

        Employee user = employeeService.findByUsername(userName);
        String firstName = user.getFirstname();
        String lastName = user.getLastname();
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("userName", userName);

        //model.addAttribute("employee", employeeService.findByUsername(un));
        //model.addAttribute("employee", );
        return "profile";
    }


    //Edit profile form
    @GetMapping("/profile/edit")
    public String profileEdit(Model model, Employee employee) {



        return"profile";
    }
}