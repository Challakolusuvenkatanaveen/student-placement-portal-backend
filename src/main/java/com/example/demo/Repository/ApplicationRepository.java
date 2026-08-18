package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByStudentStudentId(Long studentId);

    List<Application> findByJobJobId(Long jobId);

    List<Application> findByStatus(String status);

    boolean existsByStudentStudentIdAndJobJobId(Long studentId,
                                                 Long jobId);

}