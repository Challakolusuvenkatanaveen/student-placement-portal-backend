package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AdminDashboardDTO;
import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.DTO.CompanyDTO;
import com.example.demo.DTO.JobDTO;
import com.example.demo.DTO.MonthlyReportDTO;
import com.example.demo.DTO.ReportDTO;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.Entity.Application;
import com.example.demo.Entity.Company;
import com.example.demo.Entity.Job;
import com.example.demo.Entity.RecentActivity;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.ApplicationRepository;
import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.JobRepository;
import com.example.demo.Repository.RecentActivityRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private RecentActivityRepository recentActivityRepository;

    // =====================================================
    // Student Entity -> StudentDTO
    // =====================================================

    private StudentDTO convertStudentDTO(Student student) {

        StudentDTO dto = new StudentDTO();

        dto.setStudentId(student.getStudentId());
        dto.setRegisterNumber(student.getRegisterNumber());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setGender(student.getGender());
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

    // =====================================================
    // Company Entity -> CompanyDTO
    // =====================================================

    private CompanyDTO convertCompanyDTO(Company company) {

        CompanyDTO dto = new CompanyDTO();

        dto.setCompanyId(company.getCompanyId());
        dto.setCompanyName(company.getCompanyName());
        dto.setEmail(company.getEmail());
        dto.setPhone(company.getPhone());
        dto.setWebsite(company.getWebsite());
        dto.setAddress(company.getAddress());
        dto.setCity(company.getCity());
        dto.setState(company.getState());
        dto.setDescription(company.getDescription());
        dto.setLogo(company.getLogo());
        dto.setVerified(company.getVerified());

        return dto;
    }

    // =====================================================
    // Job Entity -> JobDTO
    // =====================================================

    private JobDTO convertJobDTO(Job job) {

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

    // =====================================================
    // Application Entity -> ApplicationDTO
    // =====================================================

    private ApplicationDTO convertApplicationDTO(Application application) {

        ApplicationDTO dto = new ApplicationDTO();

        dto.setApplicationId(application.getApplicationId());
        dto.setAppliedDate(application.getAppliedDate());
        dto.setStatus(application.getStatus());

        if (application.getStudent() != null) {

            Student student = application.getStudent();

            dto.setStudentId(student.getStudentId());
            dto.setStudentName(
                    student.getFirstName() + " " + student.getLastName());

            dto.setStudentEmail(student.getEmail());
            dto.setCgpa(student.getCgpa());

            if (student.getResume() != null) {
                dto.setResumeUrl(student.getResume().getFilePath());
            }
        }

        if (application.getJob() != null) {

            Job job = application.getJob();

            dto.setJobId(job.getJobId());
            dto.setJobTitle(job.getJobTitle());
        }

        return dto;
    }
    
    // =====================================================
    // Get All Students
    // =====================================================

    @Override
    public List<StudentDTO> getAllStudents() {

        List<StudentDTO> dtoList = new ArrayList<>();

        for (Student student : studentRepository.findAll()) {

            dtoList.add(convertStudentDTO(student));

        }

        return dtoList;

    }

    // =====================================================
    // Get All Companies
    // =====================================================

    @Override
    public List<CompanyDTO> getAllCompanies() {

        List<CompanyDTO> dtoList = new ArrayList<>();

        for (Company company : companyRepository.findAll()) {

            dtoList.add(convertCompanyDTO(company));

        }

        return dtoList;

    }

    // =====================================================
    // Get All Jobs
    // =====================================================

    @Override
    public List<JobDTO> getAllJobs() {

        List<JobDTO> dtoList = new ArrayList<>();

        for (Job job : jobRepository.findAll()) {

            dtoList.add(convertJobDTO(job));

        }

        return dtoList;

    }

    // =====================================================
    // Verify Student
    // =====================================================

    @Override
    public StudentDTO verifyStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)

                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        student.setVerified(true);

        student = studentRepository.save(student);

        return convertStudentDTO(student);

    }

    // =====================================================
    // Verify Company
    // =====================================================

    @Override
    public CompanyDTO verifyCompany(Long companyId) {

        Company company = companyRepository.findById(companyId)

                .orElseThrow(() ->
                        new ResourceNotFoundException("Company Not Found"));

        company.setVerified(true);

        company = companyRepository.save(company);

        return convertCompanyDTO(company);

    }

    // =====================================================
    // Delete Student
    // =====================================================

    @Override
    public void deleteStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)

                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        studentRepository.delete(student);

    }

    // =====================================================
    // Delete Company
    // =====================================================

    @Override
    public void deleteCompany(Long companyId) {

        Company company = companyRepository.findById(companyId)

                .orElseThrow(() ->
                        new ResourceNotFoundException("Company Not Found"));

        companyRepository.delete(company);

    }

    // =====================================================
    // Delete Job
    // =====================================================

    @Override
    public void deleteJob(Long jobId) {

        Job job = jobRepository.findById(jobId)

                .orElseThrow(() ->
                        new ResourceNotFoundException("Job Not Found"));

        jobRepository.delete(job);

    }
    
    // =====================================================
    // Dashboard
    // =====================================================

    @Override
    public AdminDashboardDTO getDashboard() {

        AdminDashboardDTO dashboard = new AdminDashboardDTO();

        dashboard.setTotalStudents(
                (int) studentRepository.count()
        );

        dashboard.setTotalCompanies(
                (int) companyRepository.count()
        );

        dashboard.setTotalJobs(
                (int) jobRepository.count()
        );

        dashboard.setTotalApplications(
                (int) applicationRepository.count()
        );

        dashboard.setVerifiedStudents(

                (int) studentRepository.findAll()

                        .stream()

                        .filter(student ->
                                Boolean.TRUE.equals(student.getVerified()))

                        .count()

        );

        dashboard.setVerifiedCompanies(

                (int) companyRepository.findAll()

                        .stream()

                        .filter(company ->
                                Boolean.TRUE.equals(company.getVerified()))

                        .count()

        );

        return dashboard;

    }

    // =====================================================
    // Get All Applications
    // =====================================================

    @Override
    public List<ApplicationDTO> getAllApplications() {

        List<ApplicationDTO> applicationDTOList =
                new ArrayList<>();

        List<Application> applications =
                applicationRepository.findAll();

        for (Application application : applications) {

            applicationDTOList.add(
                    convertApplicationDTO(application)
            );

        }

        return applicationDTOList;

    }

    // =====================================================
    // Recent Activities
    // =====================================================

    @Override
    public List<RecentActivity> getRecentActivities() {

        return recentActivityRepository
                .findTop10ByOrderByDateDesc();

    }
    
    // =====================================================
    // Reports
    // =====================================================

    @Override
    public ReportDTO getReport() {

        ReportDTO report = new ReportDTO();

        report.setTotalStudents(studentRepository.count());

        report.setTotalCompanies(companyRepository.count());

        report.setTotalJobs(jobRepository.count());

        report.setTotalApplications(applicationRepository.count());

        report.setSelectedStudents(

                Long.valueOf(
                        applicationRepository
                                .findByStatus("Selected")
                                .size()
                )

        );

        report.setRejectedStudents(

                Long.valueOf(
                        applicationRepository
                                .findByStatus("Rejected")
                                .size()
                )

        );

        report.setPendingApplications(

                Long.valueOf(
                        applicationRepository
                                .findByStatus("Applied")
                                .size()
                )

        );

        return report;

    }

    // =====================================================
    // Monthly Reports
    // =====================================================

    @Override
    public List<MonthlyReportDTO> getMonthlyReports() {

        List<MonthlyReportDTO> reports =
                new ArrayList<>();

        MonthlyReportDTO report =
                new MonthlyReportDTO();

        report.setMonth("Current");

        report.setStudents(
                studentRepository.count()
        );

        report.setCompanies(
                companyRepository.count()
        );

        report.setJobs(
                jobRepository.count()
        );

        report.setApplications(
                applicationRepository.count()
        );

        report.setSelections(

                Long.valueOf(
                        applicationRepository
                                .findByStatus("Selected")
                                .size()
                )

        );

        reports.add(report);

        return reports;

    }

    // =====================================================
    // Export PDF
    // =====================================================

    @Override
    public byte[] exportPdf() {

        // TODO:
        // Replace with actual PDF generation (iText/PDFBox)

        return new byte[0];

    }

    // =====================================================
    // Export Excel
    // =====================================================

    @Override
    public byte[] exportExcel() {

        // TODO:
        // Replace with actual Excel generation (Apache POI)

        return new byte[0];

    }

}