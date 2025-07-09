package com.cb_labs.cb_flow_connect.persistance.respositories;

import com.cb_labs.cb_flow_connect.persistance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, Long> {

    @Query(value = """
        SELECT u.* FROM users u
        JOIN auth_codes ac ON u.user_id = ac.user_id
        WHERE ac.code = :code
        AND (ac.deleted_at IS NULL)
    """, nativeQuery = true)
    Optional<User> findUserByValidAuthCode(String code);

    Optional<User> findByEmail(String email);

    Optional<User> findByUuid(UUID uuid);

}
