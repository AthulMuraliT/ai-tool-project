package com.aitoolfinder.ai_tools_backend.service;

import com.aitoolfinder.ai_tools_backend.entity.Review;
import com.aitoolfinder.ai_tools_backend.entity.ReviewStatus;
import com.aitoolfinder.ai_tools_backend.entity.AiTool;
import com.aitoolfinder.ai_tools_backend.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ToolService toolService;

    public ReviewService(ReviewRepository reviewRepository,
                         ToolService toolService) {
        this.reviewRepository = reviewRepository;
        this.toolService = toolService;
    }

    // User submits review → always PENDING
    public void submitReview(Long toolId, Integer rating, String comment) {

        AiTool tool = toolService.getToolOrThrow(toolId);

        Review review = new Review();
        review.setTool(tool);
        review.setRating(rating);
        review.setComment(comment);
        review.setStatus(ReviewStatus.PENDING);

        reviewRepository.save(review);
    }
}
