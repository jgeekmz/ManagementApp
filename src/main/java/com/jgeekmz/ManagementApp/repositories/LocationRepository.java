package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}