package com.example.demo.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ResumeDTO;
import com.example.demo.Entity.Resume;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.ResumeRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Entity -> DTO
    private ResumeDTO convertToDTO(Resume resume) {

        ResumeDTO dto = new ResumeDTO();

        dto.setResumeId(resume.getResumeId());
        dto.setFileName(resume.getFileName());
        dto.setFileType(resume.getFileType());
        dto.setFilePath(resume.getFilePath());

        if (resume.getStudent() != null) {
            dto.setStudentId(resume.getStudent().getStudentId());
        }

        return dto;
    }

    // DTO -> Entity
    private Resume convertToEntity(ResumeDTO dto) {

        Resume resume = new Resume();

        resume.setResumeId(dto.getResumeId());
        resume.setFileName(dto.getFileName());
        resume.setFileType(dto.getFileType());
        resume.setFilePath(dto.getFilePath());

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        resume.setStudent(student);

        return resume;
    }

    @Override
    public ResumeDTO uploadResume(ResumeDTO resumeDTO) {

        Resume resume = convertToEntity(resumeDTO);

        Resume savedResume = resumeRepository.save(resume);

        return convertToDTO(savedResume);
    }

    @Override
    public ResumeDTO getResumeByStudentId(Long studentId) {

        studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student Not Found"));

        Resume resume = resumeRepository.findByStudentStudentId(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resume Not Found"));

        return convertToDTO(resume);
    }

    @Override
    public void deleteResume(Long studentId) {

        Resume resume = resumeRepository.findByStudentStudentId(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resume Not Found"));

        resumeRepository.delete(resume);
    }

}