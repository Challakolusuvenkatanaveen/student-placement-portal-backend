package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.DTO.JobDTO;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.Entity.Job;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.JobRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.ApplicationService;
import com.example.demo.Service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationService applicationService;

    // ==========================
    // Entity -> DTO
    // ==========================

    private StudentDTO toDTO(Student student) {

        StudentDTO dto = new StudentDTO();

        dto.setStudentId(student.getStudentId());

        if (student.getUser() != null) {
            dto.setUserId(student.getUser().getUserId());
        }

        dto.setRegisterNumber(student.getRegisterNumber());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setGender(student.getGender());
        dto.setDob(student.getDob());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setCollege(student.getCollege());
        dto.setDepartment(student.getDepartment());
        dto.setYear(student.getYear());
        dto.setCgpa(student.getCgpa());
        dto.setAddress(student.getAddress());
        dto.setCity(student.getCity());
        dto.setState(student.getState());
        dto.setPincode(student.getPincode());
        dto.setProfilePhoto(student.getProfilePhoto());
        dto.setVerified(student.getVerified());

        return dto;
    }

    // ==========================
    // Register Student
    // ==========================

    @Override
    public StudentDTO registerStudent(StudentDTO dto) {

        Student student = new Student();

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());

        Student saved = studentRepository.save(student);

        return toDTO(saved);
    }

    // ==========================
    // Get Student By ID
    // ==========================

    @Override
    public StudentDTO getStudentById(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        return toDTO(student);
    }

    // ==========================
    // Get Student By User ID
    // ==========================

    @Override
    public StudentDTO getStudentByUserId(Long userId) {

        Student student = studentRepository.findByUserUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        return toDTO(student);
    }

    // ==========================
    // Update Student
    // ==========================

    @Override
    public StudentDTO updateStudent(StudentDTO dto) {

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        student.setRegisterNumber(dto.getRegisterNumber());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setGender(dto.getGender());
        student.setDob(dto.getDob());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setCollege(dto.getCollege());
        student.setDepartment(dto.getDepartment());
        student.setYear(dto.getYear());
        student.setCgpa(dto.getCgpa());
        student.setAddress(dto.getAddress());
        student.setCity(dto.getCity());
        student.setState(dto.getState());
        student.setPincode(dto.getPincode());
        student.setProfilePhoto(dto.getProfilePhoto());
        student.setVerified(dto.getVerified());

        Student saved = studentRepository.save(student);

        return toDTO(saved);
    }

    // ==========================
    // Get All Students
    // ==========================

    @Override
    public List<StudentDTO> getAllStudents() {

        List<StudentDTO> list = new ArrayList<>();

        for (Student student : studentRepository.findAll()) {
            list.add(toDTO(student));
        }

        return list;
    }

    // ==========================
    // Delete Student
    // ==========================

    @Override
    public void deleteStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        studentRepository.delete(student);
    }

    // ==========================
    // Available Jobs
    // ==========================

    @Override
    public List<JobDTO> getAvailableJobs() {

        List<JobDTO> jobs = new ArrayList<>();

        for (Job job : jobRepository.findAll()) {

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
                dto.setCompanyName(job.getCompany().getCompanyName());
            }

            jobs.add(dto);
        }

        return jobs;
    }

    // ==========================
    // Apply Job
    // ==========================

    @Override
    public ApplicationDTO applyJob(Long studentId, Long jobId) {

        return applicationService.applyJob(studentId, jobId);

    }

    // ==========================
    // Applied Jobs
    // ==========================

    @Override
    public List<ApplicationDTO> getAppliedJobs(Long studentId) {

        return applicationService.getApplicationsByStudent(studentId);

    }

}