package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.RecentActivity;

@Repository
public interface RecentActivityRepository
        extends JpaRepository<RecentActivity, Long> {

    // Get latest 10 activities
    List<RecentActivity> findTop10ByOrderByDateDesc();

}