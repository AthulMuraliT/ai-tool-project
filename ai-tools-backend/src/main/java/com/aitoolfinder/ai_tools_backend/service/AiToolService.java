package com.aitoolfinder.ai_tools_backend.service;

import com.aitoolfinder.ai_tools_backend.model.AiTool;
import com.aitoolfinder.ai_tools_backend.model.PricingType;
import com.aitoolfinder.ai_tools_backend.repository.AiToolRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class AiToolService {

    private final AiToolRepository aiToolRepository;

    public AiToolService(AiToolRepository aiToolRepository) {
        this.aiToolRepository = aiToolRepository;
    }

    /**
     * Fetch all AI tools (no filters)
     */
    public List<AiTool> getAllTools() {
        return aiToolRepository.findAll();
    }

    /**
     * Fetch single tool by ID
     */
    public Optional<AiTool> getToolById(Long id) {
        return aiToolRepository.findById(id);
    }

    /**
     * Advanced filtering logic
     * All parameters are optional
     *
     * @param category tool category (e.g. NLP)
     * @param pricing pricing type (FREE, PAID, SUBSCRIPTION)
     * @param rating minimum average rating
     */
    public List<AiTool> getFilteredTools(
            String category,
            PricingType pricing,
            BigDecimal rating
    ) {
        return aiToolRepository.findByFilters(category, pricing, rating);
    }

    public void updateAverageRating(Long toolId, Double avgRating) {
        aiToolRepository.findById(toolId).ifPresent(tool -> {
            BigDecimal rating = (avgRating == null)
                    ? BigDecimal.ZERO
                    : BigDecimal.valueOf(avgRating).setScale(1, RoundingMode.HALF_UP);

            tool.setAverageRating(rating);
            aiToolRepository.save(tool);
        });
    }

}
