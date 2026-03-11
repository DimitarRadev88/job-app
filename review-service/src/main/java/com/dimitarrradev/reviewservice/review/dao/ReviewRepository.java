package com.dimitarrradev.reviewservice.review.dao;

import com.dimitarrradev.reviewservice.review.dto.Rating;
import com.dimitarrradev.reviewservice.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByCompanyId(Long companyId);

    @Query("""
            SELECT AVG(r.rating) as avg_rating FROM Review r
            WHERE r.companyId = ?1
            GROUP BY r.companyId
            """)
    Double calculateAverageRating(Long companyId);

}
