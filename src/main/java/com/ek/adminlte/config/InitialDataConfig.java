package com.ek.adminlte.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ek.adminlte.model.User;
import com.ek.adminlte.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InitialDataConfig {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            // Create admin user if it doesn't exist
            if (userRepository.findByUsername("admin").isEmpty()) {
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("admin"));
                adminUser.setEmail("admin@example.com");
                adminUser.setRole("ROLE_ADMIN");
                userRepository.save(adminUser);
                System.out.println("Admin user created successfully");
            }

            // Create 100 dummy users if they don't exist
            for (int i = 1; i <= 100; i++) {
                String username = "user" + i;
                String email = "user" + i + "@example.com";
                if (userRepository.findByUsername(username).isEmpty()) {
                    User dummyUser = new User();
                    dummyUser.setUsername(username);
                    dummyUser.setPassword(passwordEncoder.encode("password"));
                    dummyUser.setEmail(email);
                    dummyUser.setRole("USER");
                    dummyUser.setEnabled(true);
                    userRepository.save(dummyUser);
                }
            }
            System.out.println("100 dummy users created or already exist.");
        };
    }
}
