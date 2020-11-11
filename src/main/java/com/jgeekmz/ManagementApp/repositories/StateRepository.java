package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

List<State> findAll();
}
