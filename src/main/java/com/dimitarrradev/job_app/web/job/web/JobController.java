package com.dimitarrradev.job_app.web.job.web;

import com.dimitarrradev.job_app.web.job.model.Job;
import com.dimitarrradev.job_app.web.job.service.JobService;
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
    public ResponseEntity<List<Job>> getAll() {
        return ResponseEntity
                .ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id) {
        Job job = jobService.getJob(id);
        if (job == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return ResponseEntity
                .ok(job);
    }

    @PostMapping("")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);

        return ResponseEntity
                .created(URI.create(""))
                .body("Job created");
    }

    @DeleteMapping("/{id}")
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

}
