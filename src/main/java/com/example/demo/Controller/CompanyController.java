package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.DTO.CompanyDTO;
import com.example.demo.DTO.CompanyDashboardDTO;
import com.example.demo.Entity.Company;
import com.example.demo.Entity.Job;
import com.example.demo.Service.CompanyService;
//
@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // ==========================================
    // Register Company
    // ==========================================

    @PostMapping("/register")
    public ResponseEntity<Company> registerCompany(
            @RequestBody Company company) {

        Company savedCompany = companyService.registerCompany(company);

        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    // ==========================================
    // Get Company By Login UserId
    // ==========================================

    @GetMapping("/user/{userId}")
    public ResponseEntity<CompanyDTO> getCompanyByUserId(
            @PathVariable Long userId) {

        Company company = companyService.getCompanyByUserId(userId);

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

        return ResponseEntity.ok(dto);
    }

    // ==========================================
    // Get Company By CompanyId
    // ==========================================

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanyById(
            @PathVariable Long companyId) {

        Company company = companyService.getCompanyById(companyId);

        return ResponseEntity.ok(company);
    }

    // ==========================================
    // Update Company
    // ==========================================

    @PutMapping("/update")
    public ResponseEntity<Company> updateCompany(
            @RequestBody Company company) {

        Company updatedCompany = companyService.updateCompany(company);

        return ResponseEntity.ok(updatedCompany);
    }

    // ==========================================
    // Get All Companies
    // ==========================================

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompanies() {

        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    // ==========================================
    // Delete Company
    // ==========================================

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<String> deleteCompany(
            @PathVariable Long companyId) {

        companyService.deleteCompany(companyId);

        return ResponseEntity.ok("Company Deleted Successfully");
    }

    // ==========================================
    // Get Company Posted Jobs
    // ==========================================

    @GetMapping("/jobs/{companyId}")
    public ResponseEntity<List<Job>> getPostedJobs(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                companyService.getPostedJobs(companyId)
        );
    }

    // ==========================================
    // Company Dashboard
    // ==========================================

    @GetMapping("/dashboard/{companyId}")
    public ResponseEntity<CompanyDashboardDTO> getDashboard(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                companyService.getDashboard(companyId)
        );
    }

    // ==========================================
    // Company Applications
    // ==========================================

    @GetMapping("/applications/{companyId}")
    public ResponseEntity<List<ApplicationDTO>> getApplications(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                companyService.getApplications(companyId)
        );
    }

}



