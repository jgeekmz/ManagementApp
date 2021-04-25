package com.jgeekmz.ManagementApp.TestSuiteExample;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MySecondClassTest {


    @Test
    public void mySecondMethod () {
        String obj1="Junit";
        String obj2="Junit";
        assertEquals(obj1,obj2);
    }
}
