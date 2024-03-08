package com.hms.te.service;

import java.util.List;

import java.util.Optional;

import com.hms.te.dto.EmployeeDTO;
import com.hms.te.entity.Employee;

public interface EmployeeService {

	Optional<String> registerEmployee(EmployeeDTO employeeDTO);

	List<Employee> getEmployees();

	Optional<Employee> getEmployee(String id);
	
	

	List<Employee> saveEmployees(EmployeeDTO employeeDTO);
	
	Employee updateEmployee(String id, EmployeeDTO employeeDTO);
	

}