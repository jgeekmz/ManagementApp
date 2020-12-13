package com.jgeekmz.ManagementApp;

import com.jgeekmz.ManagementApp.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ManagementAppApplicationTests {

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() throws Exception{
		assertThat(userController).isNotNull();
	}


}
