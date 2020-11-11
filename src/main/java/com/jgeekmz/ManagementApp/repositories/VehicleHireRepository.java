package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.VehicleHire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VehicleHireRepository extends JpaRepository<VehicleHire, Integer> {

}
