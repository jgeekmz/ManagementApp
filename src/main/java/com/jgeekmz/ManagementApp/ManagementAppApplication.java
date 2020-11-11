package com.jgeekmz.ManagementApp;

import com.jgeekmz.ManagementApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ManagementAppApplication {

    @Bean
    public AuditorAware<String> auditorAware() { return new SpringSecurityAuditorAware(); }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ManagementAppApplication.class, args);

        // Check if the raw password matches the hashed password int he database
        /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String password = "cocacola";
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println();
		System.out.println("Password is         : "+password);
		System.out.println("Encoded Password is : "+encodedPassword);
		System.out.println();
		boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
		System.out.println("Password : "+password +"   isPasswordMatch    : "+isPasswordMatch);
		System.out.println();*/

    }
}