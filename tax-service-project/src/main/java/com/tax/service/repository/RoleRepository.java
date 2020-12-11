package com.tax.service.repository;

import com.tax.service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
        public Role findRoleByRole (String role);
}
