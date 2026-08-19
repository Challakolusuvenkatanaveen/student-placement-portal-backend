package com.example.demo.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    // ====================================
    // Password Encoder
    // ====================================

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    // ====================================
    // Authentication Manager
    // ====================================

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();

    }

    // ====================================
    // CORS Configuration
    // ====================================

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        // ====================================
        // Allowed Origins
        // ====================================

        configuration.setAllowedOriginPatterns(
                List.of(
                        "http://localhost:5173",
                        "http://localhost:5174",
                        "https://*.vercel.app"
                )
        );

        // ====================================
        // Allowed HTTP Methods
        // ====================================

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "PATCH",
                        "OPTIONS"
                )
        );

        // ====================================
        // Allowed Headers
        // ====================================

        configuration.setAllowedHeaders(
                List.of("*")
        );

        // ====================================
        // Allow Credentials
        // ====================================

        configuration.setAllowCredentials(true);

        // ====================================
        // Register CORS Configuration
        // ====================================

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;

    }

    // ====================================
    // Security Filter
    // ====================================

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http

                // ====================================
                // Disable CSRF
                // ====================================

                .csrf(csrf -> csrf.disable())

                // ====================================
                // Enable CORS
                // ====================================

                .cors(cors ->
                        cors.configurationSource(
                                corsConfigurationSource()
                        )
                )

                // ====================================
                // Stateless Session
                // ====================================

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // ====================================
                // Authorization
                // ====================================

                .authorizeHttpRequests(auth -> auth

                        // ====================================
                        // Allow CORS Preflight Requests
                        // ====================================

                        .requestMatchers(
                                HttpMethod.OPTIONS,
                                "/**"
                        ).permitAll()

                        // ====================================
                        // Public Authentication APIs
                        // ====================================

                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()

                        // ====================================
                        // Student APIs
                        // ====================================

                        .requestMatchers(
                                "/api/student/**"
                        ).hasRole("STUDENT")

                        // ====================================
                        // Company APIs
                        // ====================================

                        .requestMatchers(
                                "/api/company/**"
                        ).hasRole("COMPANY")

                        // ====================================
                        // Admin APIs
                        // ====================================

                        .requestMatchers(
                                "/api/admin/**"
                        ).hasRole("ADMIN")

                        // ====================================
                        // Job View APIs
                        // ====================================

                        .requestMatchers(
                                "/api/jobs/all",
                                "/api/jobs/search",
                                "/api/jobs/location",
                                "/api/jobs/*"
                        ).hasAnyRole(
                                "STUDENT",
                                "COMPANY",
                                "ADMIN"
                        )

                        // ====================================
                        // Job Modify APIs
                        // ====================================

                        .requestMatchers(
                                "/api/jobs/post",
                                "/api/jobs/update",
                                "/api/jobs/delete/**"
                        ).hasAnyRole(
                                "COMPANY",
                                "ADMIN"
                        )

                        // ====================================
                        // All Other APIs
                        // ====================================

                        .anyRequest()
                        .authenticated()

                )

                // ====================================
                // JWT Filter
                // ====================================

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();

    }

}