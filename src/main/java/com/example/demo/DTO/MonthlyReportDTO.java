package com.example.demo.DTO;

public class MonthlyReportDTO {

    private String month;

    private Long students;

    private Long companies;

    private Long jobs;

    private Long applications;

    private Long selections;

    public MonthlyReportDTO() {
    }

    public MonthlyReportDTO(
            String month,
            Long students,
            Long companies,
            Long jobs,
            Long applications,
            Long selections) {

        this.month = month;
        this.students = students;
        this.companies = companies;
        this.jobs = jobs;
        this.applications = applications;
        this.selections = selections;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getStudents() {
        return students;
    }

    public void setStudents(Long students) {
        this.students = students;
    }

    public Long getCompanies() {
        return companies;
    }

    public void setCompanies(Long companies) {
        this.companies = companies;
    }

    public Long getJobs() {
        return jobs;
    }

    public void setJobs(Long jobs) {
        this.jobs = jobs;
    }

    public Long getApplications() {
        return applications;
    }

    public void setApplications(Long applications) {
        this.applications = applications;
    }

    public Long getSelections() {
        return selections;
    }

    public void setSelections(Long selections) {
        this.selections = selections;
    }
}