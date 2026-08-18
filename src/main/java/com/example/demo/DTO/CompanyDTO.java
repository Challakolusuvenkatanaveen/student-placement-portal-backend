package com.example.demo.DTO;


public class CompanyDTO {

    private Long companyId;
    private String companyName;
    private String email;
    private String phone;
    private String website;
    private String address;
    private String city;
    private String state;
    private String description;
    private String logo;
    private Boolean verified;

    // Default Constructor
    public CompanyDTO() {
    }

    // Parameterized Constructor
    public CompanyDTO(Long companyId, String companyName, String email,
            String phone, String website, String address, String city,
            String state, String description, String logo,
            Boolean verified) {

        this.companyId = companyId;
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.address = address;
        this.city = city;
        this.state = state;
        this.description = description;
        this.logo = logo;
        this.verified = verified;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

}