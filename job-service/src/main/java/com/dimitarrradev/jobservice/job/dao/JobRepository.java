package com.dimitarrradev.jobservice.job.dao;

import com.dimitarrradev.jobservice.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
