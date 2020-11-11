package com.jgeekmz.ManagementApp.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender=javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    /*//Sent registration notification
    public void sentNotification (User user) throws MailException {

        String userReg = user.toString();
        Date date = new Date();

        // sent email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("nonereply@gmail.com");
        mail.setSentDate(date);
        mail.setSubject("New registered user!");
        //mail.setText("Thank You for the registration!\n" + "\n" + "New registered user: \n" + "Username: "+ user.getUsername() + "\nPassword: " +  user.getPassword());
        mail.setText(userReg);

        javaMailSender.send(mail);
    }*/
}
