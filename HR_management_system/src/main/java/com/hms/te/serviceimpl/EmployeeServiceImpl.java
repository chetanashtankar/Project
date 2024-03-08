package com.hms.te.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.te.dto.EmployeeDTO;
import com.hms.te.entity.AppUser;
import com.hms.te.entity.Employee;
import com.hms.te.entity.Roles;
import com.hms.te.repository.AppUserRepository;
import com.hms.te.repository.EmployeeRespository;
import com.hms.te.repository.RoleRepository;
import com.hms.te.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRespository employeeRespository;
	private final RoleRepository RoleRespository;
	private final PasswordEncoder passwordEncoder;
	private final AppUserRepository appUserRepository;

	@Override
	public Optional<String> registerEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);

		Optional<Roles> role = RoleRespository.findByRoleName("ROLE_EMPLOYEE");
		if (role.isEmpty()) {
			Roles employeeRole = role.get();

			AppUser appUser = AppUser.builder().username(employeeDTO.getEmployeeId())
					.password(passwordEncoder.encode(employeeDTO.getPassword())).roles(new ArrayList()).build();
			appUser.getRoles().add(employeeRole);
			employeeRole.getAppUsers().add(appUser);
			appUserRepository.save(appUser);

		}
		return Optional.ofNullable(employeeRespository.save(employee).getEmployeeId());
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRespository.findAll();
	}

	@Override
	public Optional<Employee> getEmployee(String id) {
		return employeeRespository.findById(id);
	}

	@Override
	public List<Employee> saveEmployees(EmployeeDTO employeeDTO) {

		return (List<Employee>) employeeRespository
				.save(Employee.builder().employeeFirstName(employeeDTO.getEmployeeFirstName())
						.employeeLastName(employeeDTO.getEmployeeLastName())
						.employeeContactNumber(employeeDTO.getEmployeeContactNumber())
						.employeeDepartment(employeeDTO.getEmployeeDepartment())
						.employeeDesignation(employeeDTO.getEmployeeDesignation())
						.employeeEmailId(employeeDTO.getEmployeeEmailis()).employeeId(employeeDTO.getEmployeeId())
						.employeeJoiningDate(employeeDTO.getEmployeeJoiningDate()).build());
	}

	@Override
	public Employee updateEmployee(String id, EmployeeDTO employeeDTO) {
		Optional<Employee> existingEmployee = employeeRespository.findById(id);

		if (existingEmployee.isPresent()) {
			Employee employee = existingEmployee.get();

			return employeeRespository.save(employee);
		} else {
			throw new RuntimeException("Employee not found");
		}
	}

	

	

}