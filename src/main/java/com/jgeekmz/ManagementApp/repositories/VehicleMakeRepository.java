package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake, Integer> {

}
