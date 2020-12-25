package com.jgeekmz.ManagementApp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.List;

@Component
public class LoggedUser implements HttpSessionBindingListener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private String username;
    private ActiveUserStore activeUserStore;

    public LoggedUser(String username, ActiveUserStore activeUserStore) {
        this.username = username;
        this.activeUserStore = activeUserStore;
    }

    public LoggedUser() {
    }

    @Override
    public void valueBound (HttpSessionBindingEvent event) {
        //System.out.println(activeUserStore);
        List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (!users.contains(user.getUsername())) {
            users.add(user.getUsername());

        }
        log.info("User was logged!" + activeUserStore.getUsers());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        System.out.println(">>>>>  " + users);
        LoggedUser user = (LoggedUser) event.getValue();
        if (users.contains(user.getUsername())) {
            users.remove(user.getUsername());
        }
        log.info("User was logged out!" + activeUserStore.getUsers());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
