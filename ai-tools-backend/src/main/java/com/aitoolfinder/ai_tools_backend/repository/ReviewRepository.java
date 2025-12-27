package com.aitoolfinder.ai_tools_backend.repository;

import com.aitoolfinder.ai_tools_backend.entity.Review;
import com.aitoolfinder.ai_tools_backend.entity.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT AVG(r.rating)
        FROM Review r
        WHERE r.tool.id = :toolId
          AND r.status = :status
    """)
    Double findAverageRating(Long toolId, ReviewStatus status);
}
