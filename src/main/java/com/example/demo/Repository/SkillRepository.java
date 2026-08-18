package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Skills;

public interface SkillRepository extends JpaRepository<Skills, Long> {

    List<Skills> findByStudentStudentId(Long studentId);

    Optional<Skills> findBySkillName(String skillName);

}