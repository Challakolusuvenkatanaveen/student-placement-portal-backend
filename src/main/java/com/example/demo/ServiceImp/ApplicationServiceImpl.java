package com.example.demo.ServiceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.Entity.Application;
import com.example.demo.Entity.Job;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.ApplicationRepository;
import com.example.demo.Repository.JobRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JobRepository jobRepository;

    // Entity -> DTO
    private ApplicationDTO convertToDTO(Application application) {

        ApplicationDTO dto = new ApplicationDTO();

        dto.setApplicationId(application.getApplicationId());
        dto.setStudentId(application.getStudent().getStudentId());
        dto.setJobId(application.getJob().getJobId());
        dto.setAppliedDate(application.getAppliedDate());
        dto.setStatus(application.getStatus());

        return dto;
    }

    @Override
    public ApplicationDTO applyJob(Long studentId, Long jobId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job Not Found"));

        if (applicationRepository.existsByStudentStudentIdAndJobJobId(studentId, jobId)) {
            throw new RuntimeException("Student has already applied for this job.");
        }

        Application application = new Application();

        application.setStudent(student);
        application.setJob(job);
        application.setAppliedDate(LocalDate.now());
        application.setStatus("Applied");

        Application savedApplication = applicationRepository.save(application);

        return convertToDTO(savedApplication);
    }
    
    @Override
    public List<ApplicationDTO> getApplicationsByStudent(Long studentId) {

        studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        List<Application> applications =
                applicationRepository.findByStudentStudentId(studentId);

        List<ApplicationDTO> dtoList = new ArrayList<>();

        for (Application application : applications) {
            dtoList.add(convertToDTO(application));
        }

        return dtoList;
    }

    @Override
    public List<ApplicationDTO> getApplicationsByJob(Long jobId) {

        jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job Not Found"));

        List<Application> applications =
                applicationRepository.findByJobJobId(jobId);

        List<ApplicationDTO> dtoList = new ArrayList<>();

        for (Application application : applications) {
            dtoList.add(convertToDTO(application));
        }

        return dtoList;
    }
    
    
    @Override
    public ApplicationDTO updateApplicationStatus(Long applicationId,
                                                  String status) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application Not Found"));

        application.setStatus(status);

        Application updatedApplication =
                applicationRepository.save(application);

        return convertToDTO(updatedApplication);
    }

    @Override
    public void cancelApplication(Long applicationId) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application Not Found"));

        applicationRepository.delete(application);
    }

}