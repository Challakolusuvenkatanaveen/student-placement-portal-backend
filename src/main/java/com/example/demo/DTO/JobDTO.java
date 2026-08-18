package com.example.demo.DTO;

import java.time.LocalDate;

public class JobDTO {

    private Long jobId;
    private String jobTitle;
    private String description;
    private String location;
    private Double salary;
    private Double eligibilityCgpa;
    private String requiredSkills;
    private String experience;
    private Integer vacancy;
    private LocalDate applicationDeadline;
    private String jobType;

    private Long companyId;
    private String companyName;

    // Default Constructor
    public JobDTO() {
    }

    // Parameterized Constructor
    public JobDTO(Long jobId,
                  String jobTitle,
                  String description,
                  String location,
                  Double salary,
                  Double eligibilityCgpa,
                  String requiredSkills,
                  String experience,
                  Integer vacancy,
                  LocalDate applicationDeadline,
                  String jobType,
                  Long companyId,
                  String companyName) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.eligibilityCgpa = eligibilityCgpa;
        this.requiredSkills = requiredSkills;
        this.experience = experience;
        this.vacancy = vacancy;
        this.applicationDeadline = applicationDeadline;
        this.jobType = jobType;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getEligibilityCgpa() {
        return eligibilityCgpa;
    }

    public void setEligibilityCgpa(Double eligibilityCgpa) {
        this.eligibilityCgpa = eligibilityCgpa;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getVacancy() {
        return vacancy;
    }

    public void setVacancy(Integer vacancy) {
        this.vacancy = vacancy;
    }

    public LocalDate getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(LocalDate applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}