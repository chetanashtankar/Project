package com.hms.te;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hms.te.entity.AppUser;
import com.hms.te.entity.Roles;
import com.hms.te.repository.AppUserRepository;
import com.hms.te.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class HrManagementSystemApplication {
	private final RoleRepository roleRespository;
	private final AppUserRepository appUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(HrManagementSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Roles employee = Roles.builder().roleName("EMPLOYEE_ROLE").appUsers(new ArrayList()).build();
			Roles hrRole = Roles.builder().roleName("HR_ROLE").appUsers(new ArrayList()).build();

			AppUser appUserHR = AppUser.builder().username("USER").password("USER@123").roles(new ArrayList()).build();
			appUserHR.getRoles().add(hrRole);
			hrRole.getAppUsers().add(appUserHR);

			roleRespository.save(employee);
			roleRespository.save(hrRole);
			appUserRepository.save(appUserHR);

		};
	}

}