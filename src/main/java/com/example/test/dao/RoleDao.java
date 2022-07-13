package com.example.test.dao;

import com.example.test.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByRoleName(Role.RoleName roleName);
}
