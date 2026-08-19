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


    // =========================================================
    // Entity -> DTO
    // =========================================================

    private ApplicationDTO convertToDTO(Application application) {

        ApplicationDTO dto = new ApplicationDTO();


        // =====================================================
        // Application Details
        // =====================================================

        dto.setApplicationId(
                application.getApplicationId()
        );

        dto.setAppliedDate(
                application.getAppliedDate()
        );

        dto.setStatus(
                application.getStatus()
        );


        // =====================================================
        // Student Details
        // =====================================================

        if (application.getStudent() != null) {

            Student student = application.getStudent();

            dto.setStudentId(
                    student.getStudentId()
            );

            dto.setStudentName(
                    (student.getFirstName() != null
                            ? student.getFirstName()
                            : "")
                    + " "
                    +
                    (student.getLastName() != null
                            ? student.getLastName()
                            : "")
            );

            dto.setStudentEmail(
                    student.getEmail()
            );

            dto.setCgpa(
                    student.getCgpa()
            );
        }


        // =====================================================
        // Job Details
        // =====================================================

        if (application.getJob() != null) {

            Job job = application.getJob();

            dto.setJobId(
                    job.getJobId()
            );

            dto.setJobTitle(
                    job.getJobTitle()
            );

            dto.setLocation(
                    job.getLocation()
            );

            dto.setSalary(
                    job.getSalary()
            );

            dto.setJobType(
                    job.getJobType()
            );


            // =================================================
            // Company Details
            // =================================================

            if (job.getCompany() != null) {

                dto.setCompanyName(
                        job.getCompany().getCompanyName()
                );
            }
        }


        return dto;
    }


    // =========================================================
    // Apply Job
    // =========================================================

    @Override
    public ApplicationDTO applyJob(
            Long studentId,
            Long jobId) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student Not Found"
                                )
                        );


        Job job =
                jobRepository.findById(jobId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Job Not Found"
                                )
                        );


        if (applicationRepository
                .existsByStudentStudentIdAndJobJobId(
                        studentId,
                        jobId)) {

            throw new RuntimeException(
                    "Student has already applied for this job."
            );
        }


        Application application =
                new Application();


        application.setStudent(student);

        application.setJob(job);

        application.setAppliedDate(
                LocalDate.now()
        );

        application.setStatus(
                "Applied"
        );


        Application savedApplication =
                applicationRepository.save(
                        application
                );


        return convertToDTO(
                savedApplication
        );
    }


    // =========================================================
    // Get Applications By Student
    // =========================================================

    @Override
    public List<ApplicationDTO> getApplicationsByStudent(
            Long studentId) {

        studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student Not Found"
                        )
                );


        List<Application> applications =
                applicationRepository
                        .findByStudentStudentId(studentId);


        List<ApplicationDTO> dtoList =
                new ArrayList<>();


        for (Application application :
                applications) {

            dtoList.add(
                    convertToDTO(application)
            );
        }


        return dtoList;
    }


    // =========================================================
    // Get Applications By Job
    // =========================================================

    @Override
    public List<ApplicationDTO> getApplicationsByJob(
            Long jobId) {

        jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job Not Found"
                        )
                );


        List<Application> applications =
                applicationRepository
                        .findByJobJobId(jobId);


        List<ApplicationDTO> dtoList =
                new ArrayList<>();


        for (Application application :
                applications) {

            dtoList.add(
                    convertToDTO(application)
            );
        }


        return dtoList;
    }


    // =========================================================
    // Update Application Status
    // =========================================================

    @Override
    public ApplicationDTO updateApplicationStatus(
            Long applicationId,
            String status) {

        Application application =
                applicationRepository.findById(
                        applicationId
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application Not Found"
                        )
                );


        application.setStatus(status);


        Application updatedApplication =
                applicationRepository.save(
                        application
                );


        return convertToDTO(
                updatedApplication
        );
    }


    // =========================================================
    // Cancel Application
    // =========================================================

    @Override
    public void cancelApplication(
            Long applicationId) {

        Application application =
                applicationRepository.findById(
                        applicationId
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application Not Found"
                        )
                );


        applicationRepository.delete(
                application
        );
    }
}