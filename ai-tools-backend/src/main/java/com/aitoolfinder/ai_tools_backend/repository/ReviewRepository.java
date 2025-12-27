package com.aitoolfinder.ai_tools_backend.repository;

import com.aitoolfinder.ai_tools_backend.model.Review;
import com.aitoolfinder.ai_tools_backend.model.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByToolIdAndStatus(Long toolId, ReviewStatus status);

    @Query("""
        SELECT AVG(r.rating)
        FROM Review r
        WHERE r.toolId = :toolId AND r.status = 'APPROVED'
    """)
    Double calculateAverageRating(Long toolId);
}
