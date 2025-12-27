package com.aitoolfinder.ai_tools_backend.service;

import com.aitoolfinder.ai_tools_backend.model.Review;
import com.aitoolfinder.ai_tools_backend.model.ReviewStatus;
import com.aitoolfinder.ai_tools_backend.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AiToolService aiToolService;

    public ReviewService(ReviewRepository reviewRepository, AiToolService aiToolService) {
        this.reviewRepository = reviewRepository;
        this.aiToolService = aiToolService;
    }

    // USER submits review → status = PENDING
    public Review submitReview(Review review) {
        review.setStatus(ReviewStatus.PENDING);
        return reviewRepository.save(review);
    }

    // ADMIN approves review → recompute rating
    public void approveReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(review);

        Double avg = reviewRepository.calculateAverageRating(review.getToolId());
        aiToolService.updateAverageRating(review.getToolId(), avg);
    }
}
