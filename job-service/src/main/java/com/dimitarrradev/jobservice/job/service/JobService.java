package com.dimitarrradev.jobservice.job.service;

import com.dimitarrradev.jobservice.job.client.CompanyClient;
import com.dimitarrradev.jobservice.job.dao.JobRepository;
import com.dimitarrradev.jobservice.job.dto.JobServiceModel;
import com.dimitarrradev.jobservice.job.external.CompanyModel;
import com.dimitarrradev.jobservice.job.model.Job;
import com.dimitarrradev.jobservice.job.util.ToModelMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final ToModelMapper mapper;
    private final CompanyClient companyClient;

    @Retry(name = "retryGet", fallbackMethod = "findAllFallback")
    public List<JobServiceModel> findAll() {
        return jobRepository.findAll()
                .stream()
                .map(job -> {
                    JobServiceModel jobServiceModel = mapper.toJobServiceModel(job);
                    jobServiceModel.setCompany(getCompany(job.getCompanyId()));
                    return jobServiceModel;
                })
                .toList();
    }

    @CircuitBreaker(name = "cBreaker", fallbackMethod = "getJobFallback")
    public JobServiceModel getJob(Long id) {
        return jobRepository.findById(id)
                .map(job -> {
                    JobServiceModel jobServiceModel = mapper.toJobServiceModel(job);
                    jobServiceModel.setCompany(getCompany(job.getCompanyId()));
                    return jobServiceModel;
                })
                .orElse(null);
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
        return companyClient.getCompany(id);
    }

    private List<JobServiceModel> findAllFallback(Exception e) {
        return jobRepository.findAll()
                .stream()
                .map(mapper::toJobServiceModel)
                .toList();
    }

    private JobServiceModel getJobFallback(Long id, Exception e) {
        return jobRepository.findById(id)
                .map(mapper::toJobServiceModel)
                .orElse(null);
    }

}
