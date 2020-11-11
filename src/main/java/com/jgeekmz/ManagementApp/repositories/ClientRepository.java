package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
