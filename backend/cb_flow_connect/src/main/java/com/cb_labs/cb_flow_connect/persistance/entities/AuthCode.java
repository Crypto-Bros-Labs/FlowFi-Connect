package com.cb_labs.cb_flow_connect.persistance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "auth_codes")
public class AuthCode  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_code_id")
    private Long id;

    @Column(length = 4)
    private String code;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
