package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO.JobDTO;

public interface JobService {

    JobDTO postJob(JobDTO jobDTO);

    JobDTO updateJob(JobDTO jobDTO);

    void deleteJob(Long jobId);

    JobDTO getJobById(Long jobId);

    List<JobDTO> getAllJobs();

    List<JobDTO> searchJob(String keyword);

    List<JobDTO> getJobsByLocation(String location);

}