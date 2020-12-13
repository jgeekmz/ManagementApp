package com.jgeekmz.ManagementApp;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer(){
        super (ApplicationSecurityConfig.class);
    }
}
