package com.dimitarrradev.job_app.web.review.dao;

import com.dimitarrradev.job_app.web.review.model.Review;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByCompany_id(Long companyId);

    Optional<Review> findByIdAndCompany_id(Long reviewId, Long companyId);
}
