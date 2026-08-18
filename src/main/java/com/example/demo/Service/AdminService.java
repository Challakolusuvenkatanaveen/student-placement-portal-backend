package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO.AdminDashboardDTO;
import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.DTO.CompanyDTO;
import com.example.demo.DTO.JobDTO;
import com.example.demo.DTO.MonthlyReportDTO;
import com.example.demo.DTO.ReportDTO;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.Entity.RecentActivity;

public interface AdminService {

    // ==========================================
    // Students
    // ==========================================

    List<StudentDTO> getAllStudents();

    StudentDTO verifyStudent(Long studentId);

    void deleteStudent(Long studentId);

    // ==========================================
    // Companies
    // ==========================================

    List<CompanyDTO> getAllCompanies();

    CompanyDTO verifyCompany(Long companyId);

    void deleteCompany(Long companyId);

    // ==========================================
    // Jobs
    // ==========================================

    List<JobDTO> getAllJobs();

    void deleteJob(Long jobId);

    // ==========================================
    // Dashboard
    // ==========================================

    AdminDashboardDTO getDashboard();

    // ==========================================
    // Applications
    // ==========================================

    List<ApplicationDTO> getAllApplications();

    // ==========================================
    // Recent Activities
    // ==========================================

    List<RecentActivity> getRecentActivities();

    // ==========================================
    // Reports
    // ==========================================

    ReportDTO getReport();

    List<MonthlyReportDTO> getMonthlyReports();

    // ==========================================
    // Export Reports
    // ==========================================

    byte[] exportPdf();

    byte[] exportExcel();

}