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

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "clabes")
public class Clabe {

    @Id
    @Column(name = "clabe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36, unique = true)
    private UUID uuid;

    @Column(nullable = false, length = 18)
    private String clabe;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "liquidity_provider_id")
    private LiquidityProvider provider;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
