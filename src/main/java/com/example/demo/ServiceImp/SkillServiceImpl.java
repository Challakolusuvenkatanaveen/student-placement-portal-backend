package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.SkillDTO;
import com.example.demo.Entity.Skills;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.SkillRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private StudentRepository studentRepository;

    // =============================
    // Entity -> DTO
    // =============================

    private SkillDTO convertToDTO(Skills skill) {

        SkillDTO dto = new SkillDTO();

        dto.setSkillId(skill.getSkillId());
        dto.setSkillName(skill.getSkillName());

        // If entity field is skillLevel
        dto.setProficiency(skill.getSkillLevel());

        if (skill.getStudent() != null) {
            dto.setStudentId(skill.getStudent().getStudentId());
        }

        return dto;
    }

    // =============================
    // DTO -> Entity
    // =============================

    private Skills convertToEntity(SkillDTO dto) {

        Skills skill = new Skills();

        skill.setSkillId(dto.getSkillId());
        skill.setSkillName(dto.getSkillName());

        // If entity field is skillLevel
        skill.setSkillLevel(dto.getProficiency());

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        skill.setStudent(student);

        return skill;
    }

    // =============================
    // Add Skill
    // =============================

    @Override
    public SkillDTO addSkill(SkillDTO skillDTO) {

        Skills skill = convertToEntity(skillDTO);

        Skills savedSkill = skillRepository.save(skill);

        return convertToDTO(savedSkill);
    }

    // =============================
    // Update Skill
    // =============================

    @Override
    public SkillDTO updateSkill(SkillDTO skillDTO) {

        Skills existingSkill = skillRepository.findById(skillDTO.getSkillId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill Not Found"));

        existingSkill.setSkillName(skillDTO.getSkillName());
        existingSkill.setSkillLevel(skillDTO.getProficiency());

        Skills updatedSkill = skillRepository.save(existingSkill);

        return convertToDTO(updatedSkill);
    }

    // =============================
    // Delete Skill
    // =============================

    @Override
    public void deleteSkill(Long skillId) {

        Skills skill = skillRepository.findById(skillId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill Not Found"));

        skillRepository.delete(skill);
    }

    // =============================
    // Get Student Skills
    // =============================

    @Override
    public List<SkillDTO> getStudentSkills(Long studentId) {

        studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        List<Skills> skills =
                skillRepository.findByStudentStudentId(studentId);

        List<SkillDTO> dtoList = new ArrayList<>();

        for (Skills skill : skills) {
            dtoList.add(convertToDTO(skill));
        }

        return dtoList;
    }
}