package com.dimitarrradev.job_app.web.job.service;

import com.dimitarrradev.job_app.web.job.model.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private static long id = 1L;

    private final List<Job> jobs = new ArrayList<>();

    public List<Job> findAll() {
        return jobs;
    }

    public Job getJob(Long id) {
        return jobs.stream()
                .filter(job -> job.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void createJob(Job job) {
        job.setId(id++);
        jobs.add(job);
    }

    public boolean delete(Long id) {
        return jobs.removeIf(job -> job.getId().equals(id));
    }

    public boolean update(Long id, Job updateJob) {
        Job job = jobs.stream()
                .filter(j -> j.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (job == null) {
            return false;
        }

        job.setTitle(updateJob.getTitle());
        job.setDescription(updateJob.getDescription());
        job.setMinSalary(updateJob.getMinSalary());
        job.setMaxSalary(updateJob.getMaxSalary());
        job.setLocation(updateJob.getLocation());

        return true;
    }

}
