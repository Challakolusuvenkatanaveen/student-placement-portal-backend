package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByCompanyCompanyId(Long companyId);

    List<Job> findByJobTitleContaining(String jobTitle);

    List<Job> findByLocation(String location);

    List<Job> findByEligibilityCgpaLessThanEqual(Double cgpa);

}
