package com.aitoolfinder.ai_tools_backend.repository;

import com.aitoolfinder.ai_tools_backend.model.Review;
import com.aitoolfinder.ai_tools_backend.model.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT AVG(r.rating)
        FROM Review r
        WHERE r.tool.id = :toolId
          AND r.status = :status
    """)
    BigDecimal calculateAverageRating(
            @Param("toolId") Long toolId,
            @Param("status") ReviewStatus status
    );
}
