package com.cb_labs.cb_flow_connect.persistance.entities;

import com.cb_labs.cb_flow_connect.persistance.entities.pivots.LiquidityProviderToken;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tokens")
public class Token {

    @Id
    @Column(name = "token_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID uuid;

    private String name;

    private String network;

    private String address;

    @OneToMany(mappedBy = "token", fetch = FetchType.LAZY)
    private List<LiquidityProviderToken> liquidityProviderTokens;

}
