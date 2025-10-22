package com.cb_labs.cb_flow_connect.persistance.respositories;

import com.cb_labs.cb_flow_connect.persistance.entities.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> getRoleById(Integer id);
}
