package com.jgeekmz.ManagementApp;

import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.services.UserService;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class Test {

    @Mock
    private UserService userService;


    @org.junit.Test
    public void myFirstJUnitMethod() {
        String str = "JUnit is working fine!";
        assertEquals("JUnit is working fine!", str);
    }

    @org.junit.Test
    public void TestUserService() {
        List<User> ls;
        ls = userService.findAll();
        assertTrue(ls.isEmpty());
       // assertFalse(ls.size() > 0);
    }
}
