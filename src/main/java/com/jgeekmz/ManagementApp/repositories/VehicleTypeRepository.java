package com.jgeekmz.ManagementApp.repositories;


import com.jgeekmz.ManagementApp.models.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {

}

