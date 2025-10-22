package com.cb_labs.cb_flow_connect.persistance.entities;

import com.cb_labs.cb_flow_connect.persistance.entities.enums.CapaSupportedCountries;
import com.cb_labs.cb_flow_connect.persistance.entities.enums.KYCStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "capa_accounts")
public class CapaAccount {

    @Id
    @Column(name = "capa_account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36, unique = true)
    private UUID uuid;

    @Enumerated(value = EnumType.STRING)
    private KYCStatus kycStatus;

    @Enumerated(value = EnumType.STRING)
    private CapaSupportedCountries country;

    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
