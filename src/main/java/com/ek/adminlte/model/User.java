package com.ek.adminlte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String username;
    
    @NotBlank
    @com.fasterxml.jackson.annotation.JsonIgnore
    private String password;
    
    @NotBlank
    @Email
    private String email;
    
    private String fullName;
    private String role;
    private boolean enabled = true;

    // For DataTables compatibility: return roles as a list of objects
    @com.fasterxml.jackson.annotation.JsonProperty("roles")
    public java.util.List<java.util.Map<String, String>> getRolesList() {
        java.util.List<java.util.Map<String, String>> rolesList = new java.util.ArrayList<>();
        if (role != null) {
            java.util.Map<String, String> roleMap = new java.util.HashMap<>();
            roleMap.put("name", role);
            rolesList.add(roleMap);
        }
        return rolesList;
    }
}
