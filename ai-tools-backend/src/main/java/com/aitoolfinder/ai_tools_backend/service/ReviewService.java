package com.aitoolfinder.ai_tools_backend.service;

import com.aitoolfinder.ai_tools_backend.model.*;
import com.aitoolfinder.ai_tools_backend.repository.AiToolRepository;
import com.aitoolfinder.ai_tools_backend.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AiToolRepository aiToolRepository;

    public ReviewService(ReviewRepository reviewRepository, AiToolRepository aiToolRepository) {
        this.reviewRepository = reviewRepository;
        this.aiToolRepository = aiToolRepository;
    }

    // USER: submit review
    public Review submitReview(Long toolId, int rating, String comment) {
        AiTool tool = aiToolRepository.findById(toolId)
                .orElseThrow(() -> new RuntimeException("Tool not found"));

        Review review = new Review();
        review.setTool(tool);
        review.setRating(rating);
        review.setComment(comment);
        review.setStatus(ReviewStatus.PENDING);

        return reviewRepository.save(review);
    }

    // ADMIN: approve review
    public void approveReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(review);

        recalculateRating(review.getTool().getId());
    }

    // ADMIN: reject review
    public void rejectReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setStatus(ReviewStatus.REJECTED);
        reviewRepository.save(review);
    }

    // AVG rating logic
    private void recalculateRating(Long toolId) {
        BigDecimal avg = reviewRepository.calculateAverageRating(
                toolId,
                ReviewStatus.APPROVED
        );

        AiTool tool = aiToolRepository.findById(toolId).get();
        tool.setAverageRating(avg == null ? BigDecimal.ZERO : avg);
        aiToolRepository.save(tool);
    }
}
