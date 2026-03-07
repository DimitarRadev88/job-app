package com.dimitarrradev.jobservice.job.service;

import com.dimitarrradev.jobservice.job.dao.JobRepository;
import com.dimitarrradev.jobservice.job.dto.JobServiceModel;
import com.dimitarrradev.jobservice.job.external.CompanyModel;
import com.dimitarrradev.jobservice.job.model.Job;
import com.dimitarrradev.jobservice.job.util.ToModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final ToModelMapper mapper;

    @Autowired
    private RestClient serviceRestClient;

    public List<JobServiceModel> findAll() {

        return jobRepository.findAll()
                .stream()
                .map(job -> {
                    JobServiceModel jobServiceModel = mapper.toJobServiceModel(job);
                    jobServiceModel.setCompany(getCompany(job.getId()));
                    return jobServiceModel;
                })
                .toList();

    }

    public JobServiceModel getJob(Long id) {
        return jobRepository.findById(id)
                .map(job -> {
                    JobServiceModel jobServiceModel = mapper.toJobServiceModel(job);
                    jobServiceModel.setCompany(getCompany(job.getId()));
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
        return serviceRestClient
                .get()
                .uri("/{id}", id)
                .retrieve()
                .body(CompanyModel.class);
    }


}
