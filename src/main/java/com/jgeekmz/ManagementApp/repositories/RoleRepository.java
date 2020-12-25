package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName (@Param("name") String name);


}
