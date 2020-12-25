package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final static Logger log = LoggerFactory.getLogger(LoginController.class);
    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/login-error")
    public RedirectView loginError(Model model, HttpServletRequest request, RedirectAttributes redir, User user) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        String s = (String) session.getAttribute(String.valueOf(session));
        String usrName = user.getUsername();
        User check = userRepository.findByUsername(usrName);

        
        if (session != null) {
            String k = (String) session.getAttribute(String.valueOf(session));
            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

            if (ex instanceof BadCredentialsException) {
                redir.addFlashAttribute("messageUserNotExist", "Username or password is wrong! Contact an Administrator");
            } else if (ex instanceof DisabledException) {
                redir.addFlashAttribute("messageUserNotActive", "Your account is not active yet! Contact an Administrator");
            }

            if (check != null) {
                redir.addFlashAttribute("messageUserNotRegistered", "User is not registered!");
            }
        }

        RedirectView redirectView = new RedirectView("/login", true);
        return redirectView;
    }

}