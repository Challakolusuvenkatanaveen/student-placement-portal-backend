package com.example.demo.DTO;

import java.time.LocalDate;


public class StudentDTO {


    private Long studentId;

    // NEW
    private Long userId;


    private String registerNumber;
    private String firstName;
    private String lastName;
    private String gender;

    private LocalDate dob;

    private String email;
    private String phone;

    private String college;
    private String department;

    private Integer year;
    private Double cgpa;

    private String address;
    private String city;
    private String state;
    private String pincode;

    private String profilePhoto;

    private Boolean verified;


    public StudentDTO(){

    }


    public Long getStudentId() {
        return studentId;
    }


    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }



    // NEW

    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }





    public String getRegisterNumber() {
        return registerNumber;
    }


    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public LocalDate getDob() {
        return dob;
    }


    public void setDob(LocalDate dob) {
        this.dob = dob;
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


    public String getCollege() {
        return college;
    }


    public void setCollege(String college) {
        this.college = college;
    }


    public String getDepartment() {
        return department;
    }


    public void setDepartment(String department) {
        this.department = department;
    }


    public Integer getYear() {
        return year;
    }


    public void setYear(Integer year) {
        this.year = year;
    }


    public Double getCgpa() {
        return cgpa;
    }


    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
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


    public String getPincode() {
        return pincode;
    }


    public void setPincode(String pincode) {
        this.pincode = pincode;
    }


    public String getProfilePhoto() {
        return profilePhoto;
    }


    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }


    public Boolean getVerified() {
        return verified;
    }


    public void setVerified(Boolean verified) {
        this.verified = verified;
    }


}