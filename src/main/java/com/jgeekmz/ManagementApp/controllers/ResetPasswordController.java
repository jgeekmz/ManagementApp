package com.jgeekmz.ManagementApp.controllers;

import com.jgeekmz.ManagementApp.exceptions.UserNotFoundException;
import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.services.UserService;
import com.jgeekmz.ManagementApp.utility.Utility;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ResetPasswordController {
    private final Logger log = LoggerFactory.getLogger(ResetPasswordController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping (value="/resetPassword")
    public ModelAndView resetPass(ModelAndView modelAndView, User user) {
        log.info("Reset Password Page");

        modelAndView.addObject("user", user);
        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }

    @PostMapping(value="/resetPassword")
    public String processForgotPassword(Model model, User user, HttpServletRequest request) throws UserNotFoundException {

        String email = request.getParameter("email");
        String token = RandomString.make(45);

        log.info("Email: " + email);
        log.info("Token: " + token);

        try {
            userService.updateResetPasswordToken(token, email);
            //generate reset password link
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;

            log.info(resetPasswordLink);

            // sent an reset e-mail to the user
            sentEmail(email, resetPasswordLink);

            model.addAttribute("message", "Please check your inbox. E-mail was sent to " + email);

        } catch (UserNotFoundException | UnsupportedEncodingException | MessagingException ex) {
            model.addAttribute("error",ex.getMessage() + " ");
        }

        return "resetPassword";
    }

    private void sentEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("no-reply@admin.com","Admin Center");
        helper.setTo(email);
        String subject = "Here is the link to reset your password!";
        String content="<p>Hello,</p>"
                        + "<p>Click the link below to change your password:</p>"
                        + "\n"
                        + "<p><a href=\""  + resetPasswordLink + "\">Change my password</a></p>"
                        + "\n"
                        + "<p>Ignore this email if you do remember your password, or you have not made th request!</p>";

        helper.setText(content,true);
        helper.setSubject(subject);
        helper.setText(content, true);
        javaMailSender.send(message);
    }

    @GetMapping(value = "/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        log.info("Enters the resetForm Page!!!!!!") ;

        User user = userService.get(token);

        if(user == null) {
        model.addAttribute("title", "Reset your password");
        model.addAttribute("message", "Password was changed or the reset token is expired");
        return "resetPasswordForm";
        }

        model.addAttribute("token", token);

        return "resetPasswordForm";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.get(token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            userService.updatePassword(user, password);
            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "resetPasswordForm";
    }

}