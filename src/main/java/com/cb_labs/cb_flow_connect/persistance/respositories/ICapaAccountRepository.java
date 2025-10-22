package com.cb_labs.cb_flow_connect.persistance.respositories;

import com.cb_labs.cb_flow_connect.persistance.entities.CapaAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICapaAccountRepository extends JpaRepository<CapaAccount, Long> {
}
