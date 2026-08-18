package com.example.demo.DTO;



public class SkillDTO {

    private Long skillId;
    private String skillName;
    private String proficiency;
    private Long studentId;

    // Default Constructor
    public SkillDTO() {
    }

    // Parameterized Constructor
    public SkillDTO(Long skillId, String skillName,
                    String proficiency, Long studentId) {

        this.skillId = skillId;
        this.skillName = skillName;
        this.proficiency = proficiency;
        this.studentId = studentId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

}
