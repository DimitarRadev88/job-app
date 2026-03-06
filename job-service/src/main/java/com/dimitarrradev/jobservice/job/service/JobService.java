package com.dimitarrradev.jobservice.job.service;

import com.dimitarrradev.jobservice.job.dao.JobRepository;
import com.dimitarrradev.jobservice.job.model.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job getJob(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public void createJob(Job job) {
        jobRepository.saveAndFlush(job);
    }

    public boolean delete(Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);

        if (optionalJob.isPresent()) {
            jobRepository.delete(optionalJob.get());
            return true;
        }

        return false;
    }

    public boolean update(Long id, Job updateJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);

        if (optionalJob.isEmpty()) {
            return false;
        }

        Job job = optionalJob.get();

        job.setTitle(updateJob.getTitle());
        job.setDescription(updateJob.getDescription());
        job.setMinSalary(updateJob.getMinSalary());
        job.setMaxSalary(updateJob.getMaxSalary());
        job.setLocation(updateJob.getLocation());

        jobRepository.saveAndFlush(job);

        return true;
    }

}
