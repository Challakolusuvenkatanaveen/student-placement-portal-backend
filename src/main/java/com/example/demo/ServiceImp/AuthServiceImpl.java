package com.example.demo.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.LoginResponse;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.Entity.Company;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Security.JwtUtil;
import com.example.demo.Service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // =========================
    // REGISTER
    // =========================

    @Override
    public LoginResponse register(RegisterRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        // If you are NOT using BCrypt yet
        user.setPassword(request.getPassword());

        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);

        // =========================
        // STUDENT REGISTRATION
        // =========================

        if ("STUDENT".equalsIgnoreCase(savedUser.getRole())) {

            Student student = new Student();

            student.setFirstName(savedUser.getFullName());
            student.setEmail(savedUser.getEmail());
            student.setUser(savedUser);
            student.setVerified(false);

            studentRepository.save(student);
        }

        // =========================
        // COMPANY REGISTRATION
        // =========================

        if ("COMPANY".equalsIgnoreCase(savedUser.getRole())) {

            Company company = new Company();

            company.setCompanyName(savedUser.getFullName());
            company.setEmail(savedUser.getEmail());
            company.setUser(savedUser);
            company.setVerified(false);

            companyRepository.save(company);
        }

        String token = jwtUtil.generateToken(savedUser.getEmail());

        LoginResponse response = new LoginResponse();

        response.setUserId(savedUser.getUserId());
        response.setFullName(savedUser.getFullName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        response.setMessage("Registration Successful");
        response.setToken(token);

        return response;
    }

    // =========================
    // LOGIN
    // =========================

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email"));

        // If you are NOT using BCrypt yet
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        LoginResponse response = new LoginResponse();

        response.setUserId(user.getUserId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setMessage("Login Successful");
        response.setToken(token);

        return response;
    }
}