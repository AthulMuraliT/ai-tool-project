package com.aitoolfinder.ai_tools_backend.repository;

import com.aitoolfinder.ai_tools_backend.model.AiTool;
import com.aitoolfinder.ai_tools_backend.model.PricingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AiToolRepository extends JpaRepository<AiTool, Long> {

    /**
     * Advanced filtering:
     * - category (optional)
     * - pricing type (optional)
     * - minimum rating (optional)
     *
     * If any parameter is NULL, it is ignored.
     */
    @Query("""
        SELECT a FROM AiTool a
        WHERE (:category IS NULL OR a.category = :category)
          AND (:pricing IS NULL OR a.pricingType = :pricing)
          AND (:rating IS NULL OR a.averageRating >= :rating)
    """)
    List<AiTool> findByFilters(
            @Param("category") String category,
            @Param("pricing") PricingType pricing,
            @Param("rating") BigDecimal rating
    );
}
