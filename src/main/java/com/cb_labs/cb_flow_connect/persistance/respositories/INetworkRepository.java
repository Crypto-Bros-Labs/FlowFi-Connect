package com.cb_labs.cb_flow_connect.persistance.respositories;

import com.cb_labs.cb_flow_connect.persistance.entities.Network;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface INetworkRepository extends JpaRepository<Network, Long> {

    Optional<Network> findByUuid(UUID uuid);

}
