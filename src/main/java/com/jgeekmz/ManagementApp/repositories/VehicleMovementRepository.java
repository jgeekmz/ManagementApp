package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.VehicleMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VehicleMovementRepository extends JpaRepository<VehicleMovement, Integer> {

}
