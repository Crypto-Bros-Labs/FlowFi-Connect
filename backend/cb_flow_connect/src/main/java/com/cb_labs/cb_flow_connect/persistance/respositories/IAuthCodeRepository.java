package com.cb_labs.cb_flow_connect.persistance.respositories;

import com.cb_labs.cb_flow_connect.persistance.entities.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthCodeRepository extends JpaRepository<AuthCode, Long> {

    Optional<AuthCode> findAuthCodeByCode(String code);

}
