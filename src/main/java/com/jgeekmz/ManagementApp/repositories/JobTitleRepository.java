package com.jgeekmz.ManagementApp.repositories;

import com.jgeekmz.ManagementApp.models.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {

}
