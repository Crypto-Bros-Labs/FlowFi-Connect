package com.cb_labs.cb_flow_connect.persistance.entities.pivots;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
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
@Table(name = "liquidity_provider_token")
public class LiquidityProviderToken {

    @Id
    @Column(name = "liquidity_provider_token_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "liquidity_provider_id")
    private LiquidityProvider liquidityProvider;

    @ManyToOne
    @JoinColumn(name = "token_network_id")
    private TokenNetwork tokenNetwork;

}
