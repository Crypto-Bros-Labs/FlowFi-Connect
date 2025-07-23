package com.cb_labs.cb_flow_connect.persistance.respositories;

import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.persistance.entities.UserBankInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserBankInformationRepository extends JpaRepository<UserBankInformation, Long> {
    List<UserBankInformation> findAllByUser(User user);
    Optional<UserBankInformation> findByUuid(UUID uuid);
}
