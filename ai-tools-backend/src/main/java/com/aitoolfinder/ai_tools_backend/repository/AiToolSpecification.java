package com.aitoolfinder.ai_tools_backend.repository;

import com.aitoolfinder.ai_tools_backend.entity.AiTool;
import org.springframework.data.jpa.domain.Specification;

public class AiToolSpecification {

    public static Specification<AiTool> hasCategory(String category) {
        return (root, query, cb) ->
                category == null ? null :
                        cb.equal(cb.lower(root.get("category")), category.toLowerCase());
    }

    public static Specification<AiTool> hasPricing(String pricing) {
        return (root, query, cb) ->
                pricing == null ? null :
                        cb.equal(cb.lower(root.get("pricingType")), pricing.toLowerCase());
    }

    public static Specification<AiTool> hasMinRating(Double rating) {
        return (root, query, cb) ->
                rating == null ? null :
                        cb.greaterThanOrEqualTo(root.get("averageRating"), rating);
    }
}
