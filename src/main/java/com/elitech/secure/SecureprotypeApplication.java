package com.elitech.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elitech.secure.entity.UserInfo;
import com.elitech.secure.service.UserInfoService;

@SpringBootApplication
public class SecureprotypeApplication implements CommandLineRunner {

	@Autowired
	UserInfoService userInfoService; 

	public static void main(String[] args) {
		SpringApplication.run(SecureprotypeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
				UserInfo userInfo=new UserInfo (1,"najah","najah@gmail.com", "ADMIN_ROLES,USER_ROLES", "azerty");
				userInfoService.addUser(userInfo);		
	}

}
