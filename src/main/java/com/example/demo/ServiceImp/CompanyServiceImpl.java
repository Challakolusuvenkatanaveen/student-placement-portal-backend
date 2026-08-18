package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.DTO.CompanyDashboardDTO;
import com.example.demo.Entity.Application;
import com.example.demo.Entity.Company;
import com.example.demo.Entity.Job;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.ApplicationRepository;
import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.JobRepository;
import com.example.demo.Service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    // ==========================
    // Register Company
    // ==========================

    @Override
    public Company registerCompany(Company company) {
        return companyRepository.save(company);
    }

    // ==========================
    // Get Company By CompanyId
    // ==========================

    @Override
    public Company getCompanyById(Long companyId) {

        return companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company Not Found"));
    }

    // ==========================
    // Get Company By Login UserId
    // ==========================

    @Override
    public Company getCompanyByUserId(Long userId) {

        return companyRepository.findByUserUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company Not Found"));
    }

    // ==========================
    // Update Company
    // ==========================

    @Override
    public Company updateCompany(Company company) {

        try {

            System.out.println("====================================");
            System.out.println("UPDATE COMPANY REQUEST");
            System.out.println("Company ID      : " + company.getCompanyId());
            System.out.println("Company Name    : " + company.getCompanyName());
            System.out.println("Email           : " + company.getEmail());
            System.out.println("Phone           : " + company.getPhone());
            System.out.println("Website         : " + company.getWebsite());
            System.out.println("Address         : " + company.getAddress());
            System.out.println("City            : " + company.getCity());
            System.out.println("State           : " + company.getState());
            System.out.println("Description     : " + company.getDescription());
            System.out.println("Logo            : " + company.getLogo());
            System.out.println("Verified        : " + company.getVerified());

            Company existingCompany =
                    companyRepository.findById(company.getCompanyId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException("Company Not Found"));

            existingCompany.setCompanyName(company.getCompanyName());
            existingCompany.setEmail(company.getEmail());
            existingCompany.setPhone(company.getPhone());
            existingCompany.setWebsite(company.getWebsite());
            existingCompany.setAddress(company.getAddress());
            existingCompany.setCity(company.getCity());
            existingCompany.setState(company.getState());
            existingCompany.setDescription(company.getDescription());
            existingCompany.setLogo(company.getLogo());
            existingCompany.setVerified(company.getVerified());

            Company savedCompany = companyRepository.save(existingCompany);

            System.out.println("Company Updated Successfully");
            System.out.println("====================================");

            return savedCompany;

        } catch (Exception e) {

            System.out.println("========== UPDATE FAILED ==========");
            e.printStackTrace();
            throw e;
        }
    }

    // ==========================
    // Get All Companies
    // ==========================

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // ==========================
    // Delete Company
    // ==========================

    @Override
    public void deleteCompany(Long companyId) {

        Company company =
                companyRepository.findById(companyId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Company Not Found"));

        companyRepository.delete(company);
    }

    // ==========================
    // Get Posted Jobs
    // ==========================

    @Override
    public List<Job> getPostedJobs(Long companyId) {

        companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company Not Found"));

        return jobRepository.findByCompanyCompanyId(companyId);
    }

    // ==========================
    // Dashboard
    // ==========================

    @Override
    public CompanyDashboardDTO getDashboard(Long companyId) {

        List<Job> jobs = jobRepository.findByCompanyCompanyId(companyId);

        int totalJobs = jobs.size();
        int totalApplications = 0;
        int totalSelected = 0;
        int totalRejected = 0;

        for (Job job : jobs) {

            List<Application> applications =
                    applicationRepository.findByJobJobId(job.getJobId());

            totalApplications += applications.size();

            for (Application application : applications) {

                if ("Selected".equalsIgnoreCase(application.getStatus())) {
                    totalSelected++;
                }

                if ("Rejected".equalsIgnoreCase(application.getStatus())) {
                    totalRejected++;
                }
            }
        }

        return new CompanyDashboardDTO(
                totalJobs,
                totalApplications,
                totalSelected,
                totalRejected
        );
    }

    // ==========================
    // Company Applications
    // ==========================

    @Override
    public List<ApplicationDTO> getApplications(Long companyId) {

        List<Job> jobs = jobRepository.findByCompanyCompanyId(companyId);

        List<ApplicationDTO> dtoList = new ArrayList<>();

        for (Job job : jobs) {

            List<Application> applications =
                    applicationRepository.findByJobJobId(job.getJobId());

            for (Application application : applications) {

                ApplicationDTO dto = new ApplicationDTO();

                dto.setApplicationId(application.getApplicationId());
                dto.setJobTitle(job.getJobTitle());

                dto.setStudentName(
                        application.getStudent().getFirstName() + " "
                                + application.getStudent().getLastName());

                dto.setStudentEmail(
                        application.getStudent().getEmail());

                dto.setCgpa(
                        application.getStudent().getCgpa());

                if (application.getStudent().getResume() != null) {
                    dto.setResumeUrl(
                            application.getStudent()
                                    .getResume()
                                    .getFilePath());
                }

                dto.setStatus(application.getStatus());

                dtoList.add(dto);
            }
        }

        return dtoList;
    }
}