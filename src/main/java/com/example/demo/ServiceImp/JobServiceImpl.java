package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.JobDTO;
import com.example.demo.Entity.Company;
import com.example.demo.Entity.Job;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.JobRepository;
import com.example.demo.Service.JobService;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // ================= Entity -> DTO =================

    private JobDTO convertToDTO(Job job) {

        JobDTO dto = new JobDTO();

        dto.setJobId(job.getJobId());
        dto.setJobTitle(job.getJobTitle());
        dto.setDescription(job.getDescription());
        dto.setLocation(job.getLocation());
        dto.setSalary(job.getSalary());
        dto.setEligibilityCgpa(job.getEligibilityCgpa());
        dto.setRequiredSkills(job.getRequiredSkills());
        dto.setExperience(job.getExperience());
        dto.setVacancy(job.getVacancy());
        dto.setApplicationDeadline(job.getApplicationDeadline());
        dto.setJobType(job.getJobType());

        if (job.getCompany() != null) {
            dto.setCompanyId(job.getCompany().getCompanyId());
        }

        return dto;
    }

    // ================= DTO -> Entity =================

    private Job convertToEntity(JobDTO dto) {

        Job job = new Job();

        job.setJobId(dto.getJobId());
        job.setJobTitle(dto.getJobTitle());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());
        job.setSalary(dto.getSalary());
        job.setEligibilityCgpa(dto.getEligibilityCgpa());
        job.setRequiredSkills(dto.getRequiredSkills());
        job.setExperience(dto.getExperience());
        job.setVacancy(dto.getVacancy());
        job.setApplicationDeadline(dto.getApplicationDeadline());
        job.setJobType(dto.getJobType());

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() ->
                new ResourceNotFoundException("Company Not Found"));

        job.setCompany(company);

        return job;
    }

    // ================= Post Job =================

    @Override
    public JobDTO postJob(JobDTO jobDTO) {

        Job job = convertToEntity(jobDTO);

        Job savedJob = jobRepository.save(job);

        return convertToDTO(savedJob);
    }
    
    
    // ================= Update Job =================

    @Override
    public JobDTO updateJob(JobDTO jobDTO) {

        Job existingJob = jobRepository.findById(jobDTO.getJobId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job Not Found"));

        existingJob.setJobTitle(jobDTO.getJobTitle());
        existingJob.setDescription(jobDTO.getDescription());
        existingJob.setLocation(jobDTO.getLocation());
        existingJob.setSalary(jobDTO.getSalary());
        existingJob.setEligibilityCgpa(jobDTO.getEligibilityCgpa());
        existingJob.setRequiredSkills(jobDTO.getRequiredSkills());
        existingJob.setExperience(jobDTO.getExperience());
        existingJob.setVacancy(jobDTO.getVacancy());
        existingJob.setApplicationDeadline(jobDTO.getApplicationDeadline());
        existingJob.setJobType(jobDTO.getJobType());

        if (jobDTO.getCompanyId() != null) {

            Company company = companyRepository.findById(jobDTO.getCompanyId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Company Not Found"));

            existingJob.setCompany(company);
        }

        Job updatedJob = jobRepository.save(existingJob);

        return convertToDTO(updatedJob);
    }

    // ================= Delete Job =================

    @Override
    public void deleteJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job Not Found"));

        jobRepository.delete(job);
    }

    // ================= Get Job By Id =================

    @Override
    public JobDTO getJobById(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job Not Found"));

        return convertToDTO(job);
    }
    
    
    // ================= Get All Jobs =================

    @Override
    public List<JobDTO> getAllJobs() {

        List<Job> jobs = jobRepository.findAll();

        List<JobDTO> jobDTOList = new ArrayList<>();

        for (Job job : jobs) {

            jobDTOList.add(convertToDTO(job));

        }

        return jobDTOList;
    }

    // ================= Search Job =================

    @Override
    public List<JobDTO> searchJob(String keyword) {

        List<Job> jobs = jobRepository.findByJobTitleContaining(keyword);

        List<JobDTO> jobDTOList = new ArrayList<>();

        for (Job job : jobs) {

            jobDTOList.add(convertToDTO(job));

        }

        return jobDTOList;
    }

    // ================= Get Jobs By Location =================

    @Override
    public List<JobDTO> getJobsByLocation(String location) {

        List<Job> jobs = jobRepository.findByLocation(location);

        List<JobDTO> jobDTOList = new ArrayList<>();

        for (Job job : jobs) {

            jobDTOList.add(convertToDTO(job));

        }

        return jobDTOList;
    }

}