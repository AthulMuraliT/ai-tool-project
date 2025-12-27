package com.aitoolfinder.ai_tools_backend.service;

import com.aitoolfinder.ai_tools_backend.entity.AiTool;
import com.aitoolfinder.ai_tools_backend.entity.ReviewStatus;
import com.aitoolfinder.ai_tools_backend.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final ReviewRepository reviewRepository;
    private final ToolService toolService;

    public RatingService(ReviewRepository reviewRepository,
                         ToolService toolService) {
        this.reviewRepository = reviewRepository;
        this.toolService = toolService;
    }

    public void recalculateRating(Long toolId) {

        Double avgRating =
                reviewRepository.findAverageRating(toolId, ReviewStatus.APPROVED);

        AiTool tool = toolService.getToolOrThrow(toolId);

        tool.setAverageRating(avgRating == null ? 0.0 : avgRating);
        toolService.save(tool);
    }
}
