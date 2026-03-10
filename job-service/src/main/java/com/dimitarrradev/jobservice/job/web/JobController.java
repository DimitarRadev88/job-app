package com.dimitarrradev.jobservice.job.web;


import com.dimitarrradev.jobservice.job.dto.JobServiceModel;
import com.dimitarrradev.jobservice.job.model.Job;
import com.dimitarrradev.jobservice.job.service.JobService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("")
    public ResponseEntity<List<JobServiceModel>> getAll() {
        return ResponseEntity
                .ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobServiceModel> getJob(@PathVariable Long id) {
        JobServiceModel job = jobService.getJob(id);
        if (job == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return ResponseEntity
                .ok(job);
    }

    @PostMapping("")
    @RateLimiter(name = "createLimiter", fallbackMethod = "createFallback")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);

        return ResponseEntity
                .created(URI.create(""))
                .body("Job created");
    }

    @DeleteMapping("/{id}")
    @RateLimiter(name = "deleteLimiter", fallbackMethod = "deleteFallback")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean removed = jobService.delete(id);

        if (removed) {
            return ResponseEntity
                    .ok()
                    .body("Job deleted successfully");
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PatchMapping("/{id}")
    @RateLimiter(name = "updateLimiter", fallbackMethod = "updateFallback")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job) {
        boolean updated = jobService.update(id, job);

        if (updated) {
            return ResponseEntity
                    .ok()
                    .body("Job updated successfully");
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    private ResponseEntity<String> createFallback(Job job, Exception e) {
        return getTooManyRequests();
    }

    private ResponseEntity<String> deleteFallback(Long id, Exception e) {
        return getTooManyRequests();
    }

    private ResponseEntity<String> updateFallback(Long id, Job job, Exception e) {
        return getTooManyRequests();
    }

    private ResponseEntity<String> getTooManyRequests() {
        return ResponseEntity.status(429).body("Too many requests!");
    }

}
