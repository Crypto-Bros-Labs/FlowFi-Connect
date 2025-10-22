package com.cb_labs.cb_flow_connect.persistance.respositories;

import com.cb_labs.cb_flow_connect.persistance.entities.pivots.LiquidityProviderToken;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.TokenNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILiquidityProviderTokenRepository extends JpaRepository<LiquidityProviderToken, Long> {

    List<LiquidityProviderToken> findAllByTokenNetwork(TokenNetwork tokenNetwork);

}
