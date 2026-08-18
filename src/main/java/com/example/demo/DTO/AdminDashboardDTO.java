package com.example.demo.DTO;

public class AdminDashboardDTO {

    private Integer totalStudents;
    private Integer totalCompanies;
    private Integer totalJobs;
    private Integer totalApplications;
    private Integer verifiedStudents;
    private Integer verifiedCompanies;

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getTotalCompanies() {
        return totalCompanies;
    }

    public void setTotalCompanies(Integer totalCompanies) {
        this.totalCompanies = totalCompanies;
    }

    public Integer getTotalJobs() {
        return totalJobs;
    }

    public void setTotalJobs(Integer totalJobs) {
        this.totalJobs = totalJobs;
    }

    public Integer getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(Integer totalApplications) {
        this.totalApplications = totalApplications;
    }

    public Integer getVerifiedStudents() {
        return verifiedStudents;
    }

    public void setVerifiedStudents(Integer verifiedStudents) {
        this.verifiedStudents = verifiedStudents;
    }

    public Integer getVerifiedCompanies() {
        return verifiedCompanies;
    }

    public void setVerifiedCompanies(Integer verifiedCompanies) {
        this.verifiedCompanies = verifiedCompanies;
    }
}