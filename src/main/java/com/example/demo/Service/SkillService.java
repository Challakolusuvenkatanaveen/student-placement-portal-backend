package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO.SkillDTO;

public interface SkillService {

    SkillDTO addSkill(SkillDTO skillDTO);

    SkillDTO updateSkill(SkillDTO skillDTO);

    void deleteSkill(Long skillId);

    List<SkillDTO> getStudentSkills(Long studentId);

}