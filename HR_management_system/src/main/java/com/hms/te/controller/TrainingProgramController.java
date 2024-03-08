package com.hms.te.controller;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.te.entity.TrainingProgram;
import com.hms.te.response.SuccessResponse;
import com.hms.te.service.TrainingProgramService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
public class TrainingProgramController {

	private final TrainingProgramService trainingProgramService;

	@GetMapping("/")
	public ResponseEntity<SuccessResponse> getEmployees() {
		List<TrainingProgram> employees = trainingProgramService.getTrainingPrograms();
		return ResponseEntity.<SuccessResponse>ok().body(SuccessResponse.builder().data(employees)
				.message("Empyee data prvided").timesStamp(LocalDateTime.now()).build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse> getEmployee(@PathVariable String id) {
		TrainingProgram program = trainingProgramService.getTrainingProgram(id)
				.orElseThrow(() -> new RuntimeException("employee not found...!!"));
		return ResponseEntity.<SuccessResponse>ok()
				.body(SuccessResponse.builder().data(program).message("program data provided").build());
	}
}