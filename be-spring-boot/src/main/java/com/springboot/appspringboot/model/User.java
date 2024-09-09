package com.springboot.appspringboot.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 8, message = "Password must be between 8 and 20 characters")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    private String role;
    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private Timestamp createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    private Timestamp updatedAt;
}
