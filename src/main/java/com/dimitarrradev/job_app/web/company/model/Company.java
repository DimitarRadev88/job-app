package com.dimitarrradev.job_app.web.company.model;

import com.dimitarrradev.job_app.web.job.model.Job;
import com.dimitarrradev.job_app.web.review.model.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Job> jobs;
    @JsonIgnore
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;

}
