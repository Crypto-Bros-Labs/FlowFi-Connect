package com.cb_labs.cb_flow_connect.persistance.entities;

import com.cb_labs.cb_flow_connect.persistance.entities.enums.ProviderImplementation;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.LiquidityProviderToken;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "liquidity_providers")
public class LiquidityProvider {

    @Id
    @Column(name = "liquidity_provider_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36, unique = true)
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private ProviderImplementation implementation;

    @OneToMany(mappedBy = "liquidityProvider", fetch = FetchType.LAZY)
    List<LiquidityProviderToken> liquidityProviderTokens;

    @OneToMany(mappedBy = "clabe", fetch = FetchType.LAZY)
    List<Clabe> clabes;

}
