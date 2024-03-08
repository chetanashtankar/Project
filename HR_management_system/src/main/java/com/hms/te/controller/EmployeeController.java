package com.hms.te.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import static com.hms.te.constant.EmployeeConstant.SAVESUCCESFULLY;
import static com.hms.te.constant.EmployeeConstant.UPDATESUCESFULLY;
import org.springframework.web.bind.annotation.RestController;

import com.hms.te.dto.EmployeeDTO;
import com.hms.te.entity.Employee;
import com.hms.te.response.SuccessResponse;
import com.hms.te.service.EmployeeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping("/")
	public ResponseEntity<SuccessResponse> getEmployees() {
		List<Employee> employees = employeeService.getEmployees();
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employees)
				.message("Empyee data prvided").timesStamp(LocalDateTime.now()).build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse> getEmployee(@PathVariable String id) {
		Employee employee = employeeService.getEmployee(id)
				.orElseThrow(() -> new RuntimeException("employee not found...!!"));
		return ResponseEntity.<SuccessResponse>ok()
				.body(SuccessResponse.builder().data(employee).message("Employee data provided").build());
	}

	@PostMapping("/employee")
	public ResponseEntity<SuccessResponse> addEmployee(@RequestBody EmployeeDTO employeeDTO) {

		List<Employee> saveEmployees = employeeService.saveEmployees(employeeDTO);

		return ResponseEntity.<SuccessResponse>ok(SuccessResponse.builder().data(saveEmployees).message(SAVESUCCESFULLY)
				.timesStamp(LocalDateTime.now()).build());

	}

	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse> updateEmployee(@PathVariable String id,
			@RequestBody EmployeeDTO employeeDTO) {
		Optional<Employee> existingEmployee = employeeService.getEmployee(id);

		Employee updatedEmployee = employeeService.updateEmployee(id, employeeDTO);

		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(updatedEmployee)
				.message(UPDATESUCESFULLY).timesStamp(LocalDateTime.now()).build());

	}
	
	
	

}