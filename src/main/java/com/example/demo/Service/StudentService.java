package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.DTO.JobDTO;
import com.example.demo.DTO.StudentDTO;


public interface StudentService {


    // Register Student
    StudentDTO registerStudent(
            StudentDTO studentDTO
    );


    // Get Student by studentId
    StudentDTO getStudentById(
            Long studentId
    );


    // Get Student by login userId
    StudentDTO getStudentByUserId(
            Long userId
    );


    // Update Student
    StudentDTO updateStudent(
            StudentDTO studentDTO
    );


    // Get All Students
    List<StudentDTO> getAllStudents();


    // Delete Student
    void deleteStudent(
            Long studentId
    );


    // Available Jobs
    List<JobDTO> getAvailableJobs();


    // Apply Job
    ApplicationDTO applyJob(
            Long studentId,
            Long jobId
    );


    // Applied Jobs
    List<ApplicationDTO> getAppliedJobs(
            Long studentId
    );


}