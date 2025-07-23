package com.cb_labs.cb_flow_connect.persistance.respositories;

import com.cb_labs.cb_flow_connect.persistance.entities.FiatCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IFiatCurrencyRepository extends JpaRepository<FiatCurrency, Long> {

    Optional<FiatCurrency> findByUuid(UUID uuid);

}
