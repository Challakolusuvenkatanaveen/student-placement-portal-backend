package com.example.demo.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.ResumeDTO;
import com.example.demo.Service.ResumeService;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "*")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    // Upload Resume
    @PostMapping("/upload")
    public ResponseEntity<ResumeDTO> uploadResume(
            @RequestBody ResumeDTO resumeDTO) {

        ResumeDTO resume = resumeService.uploadResume(resumeDTO);

        return new ResponseEntity<>(resume, HttpStatus.CREATED);
    }

    // Get Resume By Student Id
    @GetMapping("/{studentId}")
    public ResponseEntity<ResumeDTO> getResumeByStudentId(
            @PathVariable Long studentId) {

        ResumeDTO resume =
                resumeService.getResumeByStudentId(studentId);

        return ResponseEntity.ok(resume);
    }

    // Delete Resume
    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<String> deleteResume(
            @PathVariable Long studentId) {

        resumeService.deleteResume(studentId);

        return ResponseEntity.ok("Resume Deleted Successfully");
    }

}