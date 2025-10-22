package com.cb_labs.cb_flow_connect.persistance.entities.pivots;

import com.cb_labs.cb_flow_connect.persistance.entities.Network;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "token_networks")
public class TokenNetwork {

    @Id
    @Column(name = "token_network_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36, unique = true)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "token_id")
    private Token token;

    @ManyToOne
    @JoinColumn(name = "network_id")
    private Network network;

    @OneToMany(mappedBy = "tokenNetwork", fetch = FetchType.LAZY)
    private List<LiquidityProviderToken> liquidityProviderTokens;

}
