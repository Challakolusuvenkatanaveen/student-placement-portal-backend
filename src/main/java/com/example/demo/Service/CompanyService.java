package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.DTO.CompanyDashboardDTO;
import com.example.demo.Entity.Company;
import com.example.demo.Entity.Job;

public interface CompanyService {

    // ==========================
    // Register Company
    // ==========================
    Company registerCompany(Company company);

    // ==========================
    // Get Company by Company ID
    // ==========================
    Company getCompanyById(Long companyId);

    // ==========================
    // Get Company by Login User ID
    // ==========================
    Company getCompanyByUserId(Long userId);

    // ==========================
    // Update Company
    // ==========================
    Company updateCompany(Company company);

    // ==========================
    // Get All Companies
    // ==========================
    List<Company> getAllCompanies();

    // ==========================
    // Delete Company
    // ==========================
    void deleteCompany(Long companyId);

    // ==========================
    // Get Jobs Posted by Company
    // ==========================
    List<Job> getPostedJobs(Long companyId);

    // ==========================
    // Company Dashboard
    // ==========================
    CompanyDashboardDTO getDashboard(Long companyId);

    // ==========================
    // Company Applications
    // ==========================
    List<ApplicationDTO> getApplications(Long companyId);

}