package com.cb_labs.cb_flow_connect.persistance.respositories;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ILiquidityProviderRepository extends JpaRepository<LiquidityProvider, Long> {

    Optional<LiquidityProvider> findByUuid(UUID uuid);

    @Query(value = """
        SELECT lp FROM LiquidityProvider lp
        JOIN lp.liquidityProviderTokens lpt
        WHERE lp.uuid = :uuid AND lpt.token = :token
    """)
    Optional<LiquidityProvider> findByUuidToken(@Param("uuid") UUID uuid, @Param("token") Token token);

}
