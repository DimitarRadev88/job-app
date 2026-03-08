package com.dimitarrradev.jobservice.job.util;

import com.dimitarrradev.jobservice.job.dao.JobRepository;
import com.dimitarrradev.jobservice.job.model.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsoleRunner implements CommandLineRunner {

    private final JobRepository jobRepository;

    @Override
    public void run(String... args) throws Exception {
        jobRepository.saveAndFlush(new Job(null,
                "Job Title",
                "Job description",
                "100",
                "100000",
                "Somewhere",
                1L)
        );
    }
}
