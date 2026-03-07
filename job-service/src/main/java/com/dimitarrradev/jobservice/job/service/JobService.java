package com.dimitarrradev.jobservice.job.service;

import com.dimitarrradev.jobservice.job.dao.JobRepository;
import com.dimitarrradev.jobservice.job.dto.JobServiceModel;
import com.dimitarrradev.jobservice.job.external.CompanyModel;
import com.dimitarrradev.jobservice.job.model.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final RestClient companyClient;

    public List<JobServiceModel> findAll() {

        return jobRepository.findAll()
                .stream()
                .map(this::map)
                .toList();

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

    private CompanyModel getCompany(Long id) {
        return companyClient
                .get()
                .uri("/{id}", id)
                .retrieve()
                .body(CompanyModel.class);
    }

    private JobServiceModel map(Job job) {
        return new JobServiceModel(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getLocation(),
                getCompany(job.getCompanyId())
        );
    }

}
