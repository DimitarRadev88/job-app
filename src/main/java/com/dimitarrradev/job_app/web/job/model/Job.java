package com.dimitarrradev.job_app.web.job.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

}
