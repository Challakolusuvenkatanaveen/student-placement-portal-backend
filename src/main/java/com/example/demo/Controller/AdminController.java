package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.AdminDashboardDTO;
import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.DTO.CompanyDTO;
import com.example.demo.DTO.JobDTO;
import com.example.demo.DTO.MonthlyReportDTO;
import com.example.demo.DTO.ReportDTO;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.Entity.RecentActivity;
import com.example.demo.Service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // ======================================================
    // Students
    // ======================================================

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {

        return ResponseEntity.ok(adminService.getAllStudents());

    }

    @PutMapping("/verify/student/{studentId}")
    public ResponseEntity<StudentDTO> verifyStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                adminService.verifyStudent(studentId)
        );

    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long studentId) {

        adminService.deleteStudent(studentId);

        return ResponseEntity.ok(
                "Student Deleted Successfully"
        );

    }

    // ======================================================
    // Companies
    // ======================================================

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {

        return ResponseEntity.ok(
                adminService.getAllCompanies()
        );

    }

    @PutMapping("/verify/company/{companyId}")
    public ResponseEntity<CompanyDTO> verifyCompany(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                adminService.verifyCompany(companyId)
        );

    }

    @DeleteMapping("/company/{companyId}")
    public ResponseEntity<String> deleteCompany(
            @PathVariable Long companyId) {

        adminService.deleteCompany(companyId);

        return ResponseEntity.ok(
                "Company Deleted Successfully"
        );

    }

    // ======================================================
    // Jobs
    // ======================================================

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> getAllJobs() {

        return ResponseEntity.ok(
                adminService.getAllJobs()
        );

    }

    @DeleteMapping("/job/{jobId}")
    public ResponseEntity<String> deleteJob(
            @PathVariable Long jobId) {

        adminService.deleteJob(jobId);

        return ResponseEntity.ok(
                "Job Deleted Successfully"
        );

    }

    // ======================================================
    // Dashboard
    // ======================================================

    @GetMapping("/dashboard")
    public ResponseEntity<AdminDashboardDTO> getDashboard() {

        return ResponseEntity.ok(
                adminService.getDashboard()
        );

    }

    // ======================================================
    // Applications
    // ======================================================

    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationDTO>> getApplications() {

        return ResponseEntity.ok(
                adminService.getAllApplications()
        );

    }

    // ======================================================
    // Recent Activities
    // ======================================================

    @GetMapping("/recent-activities")
    public ResponseEntity<List<RecentActivity>> getRecentActivities() {

        return ResponseEntity.ok(
                adminService.getRecentActivities()
        );

    }

    // ======================================================
    // Reports
    // ======================================================

    @GetMapping("/reports")
    public ResponseEntity<ReportDTO> getReports() {

        return ResponseEntity.ok(
                adminService.getReport()
        );

    }

    @GetMapping("/reports/monthly")
    public ResponseEntity<List<MonthlyReportDTO>> getMonthlyReports() {

        return ResponseEntity.ok(
                adminService.getMonthlyReports()
        );

    }

    // ======================================================
    // Export PDF
    // ======================================================

    @GetMapping("/reports/pdf")
    public ResponseEntity<byte[]> exportPdf() {

        byte[] pdf = adminService.exportPdf();

        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=Placement_Report.pdf"
                )

                .contentType(MediaType.APPLICATION_PDF)

                .body(pdf);

    }

    // ======================================================
    // Export Excel
    // ======================================================

    @GetMapping("/reports/excel")
    public ResponseEntity<byte[]> exportExcel() {

        byte[] excel = adminService.exportExcel();

        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=Placement_Report.xlsx"
                )

                .contentType(MediaType.APPLICATION_OCTET_STREAM)

                .body(excel);

    }

}