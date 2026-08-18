package com.example.demo.DTO;

public class ReportDTO {

    private Long totalStudents;
    private Long totalCompanies;
    private Long totalJobs;
    private Long totalApplications;
    private Long selectedStudents;
    private Long rejectedStudents;
    private Long pendingApplications;

    public ReportDTO() {
    }

    public ReportDTO(
            Long totalStudents,
            Long totalCompanies,
            Long totalJobs,
            Long totalApplications,
            Long selectedStudents,
            Long rejectedStudents,
            Long pendingApplications) {

        this.totalStudents = totalStudents;
        this.totalCompanies = totalCompanies;
        this.totalJobs = totalJobs;
        this.totalApplications = totalApplications;
        this.selectedStudents = selectedStudents;
        this.rejectedStudents = rejectedStudents;
        this.pendingApplications = pendingApplications;
    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Long getTotalCompanies() {
        return totalCompanies;
    }

    public void setTotalCompanies(Long totalCompanies) {
        this.totalCompanies = totalCompanies;
    }

    public Long getTotalJobs() {
        return totalJobs;
    }

    public void setTotalJobs(Long totalJobs) {
        this.totalJobs = totalJobs;
    }

    public Long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(Long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public Long getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(Long selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public Long getRejectedStudents() {
        return rejectedStudents;
    }

    public void setRejectedStudents(Long rejectedStudents) {
        this.rejectedStudents = rejectedStudents;
    }

    public Long getPendingApplications() {
        return pendingApplications;
    }

    public void setPendingApplications(Long pendingApplications) {
        this.pendingApplications = pendingApplications;
    }
}