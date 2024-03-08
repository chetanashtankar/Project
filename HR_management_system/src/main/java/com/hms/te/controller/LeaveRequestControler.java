package com.hms.te.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.te.entity.LeaveRequest;
import com.hms.te.response.SuccessResponse;
import com.hms.te.service.LeaveRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestControler {
	private final LeaveRequestService leaveRequestService;

	@GetMapping("/")
	public ResponseEntity<SuccessResponse> getLeaveRequests() {
		List<LeaveRequest> leaveRequests = leaveRequestService.getLeaveRequests();
		return ResponseEntity.<SuccessResponse>ok()
				.body(SuccessResponse.builder().data(leaveRequests).message("leave requests provided").build());
	}

	@GetMapping("/{id}`")
	public ResponseEntity<SuccessResponse> getLeaveRequest(@PathVariable Integer id) {
		LeaveRequest leaveRequest = leaveRequestService.getLeaveRequest(id)
				.orElseThrow(() -> new RuntimeException("leaveRequest not found...!!"));
		return ResponseEntity
				.<SuccessResponse>ok(SuccessResponse.builder().data(leaveRequest).message("data provided").build());
	}

}