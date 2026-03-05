package com.dimitarrradev.job_app.web.review.model;

import com.dimitarrradev.job_app.web.company.model.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double rating;
    @ManyToOne
    private Company company;

}
