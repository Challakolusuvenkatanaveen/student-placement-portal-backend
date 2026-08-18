package com.example.demo.DTO;


public class CompanyDashboardDTO {

    private Integer totalJobs;

    private Integer totalApplications;

    private Integer totalSelected;

    private Integer totalRejected;

    public CompanyDashboardDTO() {
    }

    public CompanyDashboardDTO(Integer totalJobs,
                               Integer totalApplications,
                               Integer totalSelected,
                               Integer totalRejected) {

        this.totalJobs = totalJobs;
        this.totalApplications = totalApplications;
        this.totalSelected = totalSelected;
        this.totalRejected = totalRejected;
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

    public Integer getTotalSelected() {
        return totalSelected;
    }

    public void setTotalSelected(Integer totalSelected) {
        this.totalSelected = totalSelected;
    }

    public Integer getTotalRejected() {
        return totalRejected;
    }

    public void setTotalRejected(Integer totalRejected) {
        this.totalRejected = totalRejected;
    }

}