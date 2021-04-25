package com.jgeekmz.ManagementApp;

import com.jgeekmz.ManagementApp.controllers.UserController;
import com.jgeekmz.ManagementApp.services.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ManagementAppApplicationTests {

	@Autowired
	private UserController userController;
	@Autowired
	private UserService userService;

	public ManagementAppApplicationTests(UserController userController, UserService userService) {
		this.userController = userController;
		this.userService = userService;
	}


	@Test
	void contextLoads() throws Exception{
		assertThat(userController).isNotNull();
	}


}
