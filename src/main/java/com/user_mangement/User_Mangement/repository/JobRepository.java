package com.user_mangement.User_Mangement.repository;

import com.user_mangement.User_Mangement.model.JobList;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<JobList, Long> {
    List<JobList> findByUserId(Long userId);
}
