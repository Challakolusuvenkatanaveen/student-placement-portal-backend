package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.Service.ApplicationService;

@RestController
@RequestMapping("/api/application")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Apply for Job
    @PostMapping("/apply/{studentId}/{jobId}")
    public ResponseEntity<ApplicationDTO> applyJob(
            @PathVariable Long studentId,
            @PathVariable Long jobId) {

        ApplicationDTO application =
                applicationService.applyJob(studentId, jobId);

        return new ResponseEntity<>(application, HttpStatus.CREATED);
    }

    // Get Applications by Student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByStudent(
            @PathVariable Long studentId) {

        List<ApplicationDTO> applications =
                applicationService.getApplicationsByStudent(studentId);

        return ResponseEntity.ok(applications);
    }

    // Get Applications by Job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByJob(
            @PathVariable Long jobId) {

        List<ApplicationDTO> applications =
                applicationService.getApplicationsByJob(jobId);

        return ResponseEntity.ok(applications);
    }

    // Update Application Status
    @PutMapping("/status/{applicationId}")
    public ResponseEntity<ApplicationDTO> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam String status) {

        ApplicationDTO application =
                applicationService.updateApplicationStatus(applicationId, status);

        return ResponseEntity.ok(application);
    }

    // Cancel Application
    @DeleteMapping("/cancel/{applicationId}")
    public ResponseEntity<String> cancelApplication(
            @PathVariable Long applicationId) {

        applicationService.cancelApplication(applicationId);

        return ResponseEntity.ok("Application Cancelled Successfully");
    }

}