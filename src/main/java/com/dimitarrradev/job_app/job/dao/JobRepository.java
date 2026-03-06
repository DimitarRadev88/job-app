package com.dimitarrradev.job_app.job.dao;

import com.dimitarrradev.job_app.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
