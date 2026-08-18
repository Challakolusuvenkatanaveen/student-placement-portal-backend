package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.JobDTO;
import com.example.demo.Service.JobService;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    private JobService jobService;

    // Post Job
    @PostMapping("/post")
    public ResponseEntity<JobDTO> postJob(@RequestBody JobDTO jobDTO) {

        JobDTO savedJob = jobService.postJob(jobDTO);

        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    // Update Job
    @PutMapping("/update")
    public ResponseEntity<JobDTO> updateJob(@RequestBody JobDTO jobDTO) {

        JobDTO updatedJob = jobService.updateJob(jobDTO);

        return ResponseEntity.ok(updatedJob);
    }

    // Delete Job
    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable Long jobId) {

        jobService.deleteJob(jobId);

        return ResponseEntity.ok("Job Deleted Successfully");
    }

    // Get Job By Id
    @GetMapping("/{jobId}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long jobId) {

        JobDTO job = jobService.getJobById(jobId);

        return ResponseEntity.ok(job);
    }

    // Get All Jobs
    @GetMapping("/all")
    public ResponseEntity<List<JobDTO>> getAllJobs() {

        List<JobDTO> jobs = jobService.getAllJobs();

        return ResponseEntity.ok(jobs);
    }

    // Search Job
    @GetMapping("/search")
    public ResponseEntity<List<JobDTO>> searchJob(
            @RequestParam String keyword) {

        List<JobDTO> jobs = jobService.searchJob(keyword);

        return ResponseEntity.ok(jobs);
    }

    // Get Jobs By Location
    @GetMapping("/location")
    public ResponseEntity<List<JobDTO>> getJobsByLocation(
            @RequestParam String location) {

        List<JobDTO> jobs = jobService.getJobsByLocation(location);

        return ResponseEntity.ok(jobs);
    }

}