package com.example.demo.Service;

import com.example.demo.DTO.ResumeDTO;

public interface ResumeService {

    ResumeDTO uploadResume(ResumeDTO resumeDTO);

    ResumeDTO getResumeByStudentId(Long studentId);

    void deleteResume(Long studentId);

}