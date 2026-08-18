package com.example.demo.DTO;


public class ResumeDTO {

    private Long resumeId;
    private String fileName;
    private String fileType;
    private String filePath;
    private Long studentId;

    public ResumeDTO() {
    }

    public ResumeDTO(Long resumeId, String fileName, String fileType,
                     String filePath, Long studentId) {
        this.resumeId = resumeId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.studentId = studentId;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

}