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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "users_bank_information")
public class UserBankInformation {

    @Id
    @Column(name = "user_bank_information_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36, unique = true)
    private UUID uuid;

    private String clabe;

    private String country;

    private String bankName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
