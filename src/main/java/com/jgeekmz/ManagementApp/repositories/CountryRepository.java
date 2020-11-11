package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {

}
