package com.example.demo.Service;


import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.LoginResponse;
import com.example.demo.DTO.RegisterRequest;


public interface AuthService {


    // Register User
    LoginResponse register(
            RegisterRequest registerRequest
    );


    // Login User
    LoginResponse login(
            LoginRequest loginRequest
    );


}