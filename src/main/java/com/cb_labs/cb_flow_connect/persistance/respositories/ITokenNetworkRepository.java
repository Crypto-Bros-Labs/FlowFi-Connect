package com.cb_labs.cb_flow_connect.persistance.respositories;

import com.cb_labs.cb_flow_connect.persistance.entities.pivots.TokenNetwork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITokenNetworkRepository extends JpaRepository<TokenNetwork, Long> {

    Optional<TokenNetwork> findByUuid(UUID uuid);

    Page<TokenNetwork> findAll(Specification specification, Pageable pageRequest);

}
