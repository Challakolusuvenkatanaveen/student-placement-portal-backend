package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.SkillDTO;
import com.example.demo.Service.SkillService;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "*")
public class SkillsController {

    @Autowired
    private SkillService skillService;

    // Add Skill
    @PostMapping("/add")
    public ResponseEntity<SkillDTO> addSkill(@RequestBody SkillDTO skillDTO) {

        SkillDTO savedSkill = skillService.addSkill(skillDTO);

        return new ResponseEntity<>(savedSkill, HttpStatus.CREATED);
    }

    // Update Skill
    @PutMapping("/update")
    public ResponseEntity<SkillDTO> updateSkill(@RequestBody SkillDTO skillDTO) {

        SkillDTO updatedSkill = skillService.updateSkill(skillDTO);

        return ResponseEntity.ok(updatedSkill);
    }

    // Delete Skill
    @DeleteMapping("/delete/{skillId}")
    public ResponseEntity<String> deleteSkill(@PathVariable Long skillId) {

        skillService.deleteSkill(skillId);

        return ResponseEntity.ok("Skill Deleted Successfully");
    }

    // Get Skills By Student Id
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SkillDTO>> getStudentSkills(
            @PathVariable Long studentId) {

        List<SkillDTO> skills = skillService.getStudentSkills(studentId);

        return ResponseEntity.ok(skills);
    }

}