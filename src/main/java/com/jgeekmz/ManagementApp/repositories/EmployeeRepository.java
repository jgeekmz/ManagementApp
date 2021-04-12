package com.jgeekmz.ManagementApp.repositories;

import java.util.List;

import com.jgeekmz.ManagementApp.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Employee findByUsername(String un);

    long count();

    @Query(value = "select * from employee e where e.firstname like %:keywordSearch% or e.lastname like %:keywordSearch%", nativeQuery = true)
    List<Employee> findEmployeeByKeyword(@Param("keywordSearch") String keywordSearch);

}
