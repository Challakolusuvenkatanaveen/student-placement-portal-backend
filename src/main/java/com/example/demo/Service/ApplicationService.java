package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO.ApplicationDTO;

public interface ApplicationService {

    ApplicationDTO applyJob(Long studentId, Long jobId);

    List<ApplicationDTO> getApplicationsByStudent(Long studentId);

    List<ApplicationDTO> getApplicationsByJob(Long jobId);

    ApplicationDTO updateApplicationStatus(Long applicationId, String status);

    void cancelApplication(Long applicationId);

}