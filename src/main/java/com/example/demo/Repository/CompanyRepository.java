package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    // Find company by email
    Optional<Company> findByEmail(String email);

    // Find company by company name
    Optional<Company> findByCompanyName(String companyName);

    // Find all verified companies
    List<Company> findByVerified(Boolean verified);

    // IMPORTANT
    // Find company using logged-in user's ID
    Optional<Company> findByUserUserId(Long userId);

}