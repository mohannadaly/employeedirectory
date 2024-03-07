package com.mohanad.springpractice.dao;

import com.mohanad.springpractice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByAuthority(String authority);
}
