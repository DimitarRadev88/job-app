package com.dimitarrradev.jobservice.job.util;

import com.dimitarrradev.jobservice.job.dto.JobServiceModel;
import com.dimitarrradev.jobservice.job.model.Job;
import org.springframework.stereotype.Component;

@Component
public class ToModelMapper {

    public JobServiceModel toJobServiceModel(Job job) {
        return new JobServiceModel(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getLocation(),
                null
        );
    }

}
