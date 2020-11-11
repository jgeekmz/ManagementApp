package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

}

