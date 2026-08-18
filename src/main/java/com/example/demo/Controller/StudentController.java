package com.example.demo.Controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.demo.DTO.ApplicationDTO;
import com.example.demo.DTO.JobDTO;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.Service.StudentService;



@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "*")
public class StudentController {


    @Autowired
    private StudentService studentService;




    // ==========================
    // Register Student
    // ==========================

    @PostMapping("/register")
    public ResponseEntity<StudentDTO> registerStudent(
            @RequestBody StudentDTO studentDTO
    ){


        StudentDTO savedStudent =
                studentService
                .registerStudent(studentDTO);


        return new ResponseEntity<>(
                savedStudent,
                HttpStatus.CREATED
        );

    }






    // ==========================
    // Get Student By Login UserId
    // IMPORTANT : keep before /{studentId}
    // ==========================


    @GetMapping("/user/{userId}")
    public ResponseEntity<StudentDTO> getStudentByUserId(
            @PathVariable Long userId
    ){


        StudentDTO student =
                studentService
                .getStudentByUserId(userId);


        return ResponseEntity.ok(student);

    }









    // ==========================
    // Get Student By StudentId
    // ==========================


    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(
            @PathVariable Long studentId
    ){


        StudentDTO student =
                studentService
                .getStudentById(studentId);


        return ResponseEntity.ok(student);

    }









    // ==========================
    // Update Student
    // ==========================


    @PutMapping("/update")
    public ResponseEntity<StudentDTO> updateStudent(
            @RequestBody StudentDTO studentDTO
    ){


        StudentDTO updatedStudent =
                studentService
                .updateStudent(studentDTO);


        return ResponseEntity.ok(
                updatedStudent
        );

    }










    // ==========================
    // Get All Students
    // ==========================


    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){


        List<StudentDTO> students =
                studentService
                .getAllStudents();


        return ResponseEntity.ok(
                students
        );

    }










    // ==========================
    // Delete Student
    // ==========================


    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long studentId
    ){


        studentService
        .deleteStudent(studentId);


        return ResponseEntity.ok(
                "Student Deleted Successfully"
        );

    }










    // ==========================
    // View Available Jobs
    // ==========================


    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> getAvailableJobs(){


        List<JobDTO> jobs =
                studentService
                .getAvailableJobs();


        return ResponseEntity.ok(
                jobs
        );

    }









    // ==========================
    // Apply Job
    // ==========================


    @PostMapping("/apply/{studentId}/{jobId}")
    public ResponseEntity<ApplicationDTO> applyJob(
            @PathVariable Long studentId,
            @PathVariable Long jobId
    ){


        ApplicationDTO application =
                studentService
                .applyJob(
                        studentId,
                        jobId
                );


        return new ResponseEntity<>(
                application,
                HttpStatus.CREATED
        );

    }










    // ==========================
    // View Applied Jobs
    // ==========================


    @GetMapping("/applications/{studentId}")
    public ResponseEntity<List<ApplicationDTO>> getAppliedJobs(
            @PathVariable Long studentId
    ){


        List<ApplicationDTO> applications =
                studentService
                .getAppliedJobs(studentId);


        return ResponseEntity.ok(
                applications
        );

    }


}