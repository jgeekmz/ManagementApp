package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
