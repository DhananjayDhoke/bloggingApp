package com.bloggingapp.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bloggingapp.demo.entites.Role;
import com.bloggingapp.demo.repositery.RoleRepo;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
    
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		try {
			
			Role role = new Role();
			role.setId(1);
			role.setName("ROLE_ADMIN");
			
			Role role1 = new Role();
			role.setId(2);
			role.setName("ROLE_NORMAL");
			
			List<Role> roles = List.of(role,role1);
			
			roleRepo.saveAll(roles);
			
		} catch (Exception e) {
			
		}
		
	}
	
	

}
