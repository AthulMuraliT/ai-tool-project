package com.aitoolfinder.ai_tools_backend.controller;

import com.aitoolfinder.ai_tools_backend.entity.Review;
import com.aitoolfinder.ai_tools_backend.entity.ReviewStatus;
import com.aitoolfinder.ai_tools_backend.repository.ReviewRepository;
import com.aitoolfinder.ai_tools_backend.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/reviews")
public class AdminReviewController {

    private final ReviewRepository reviewRepository;
    private final RatingService ratingService;

    public AdminReviewController(ReviewRepository reviewRepository,
                                 RatingService ratingService) {
        this.reviewRepository = reviewRepository;
        this.ratingService = ratingService;
    }

    // PUT /admin/reviews/{id}/approve
    @PutMapping("/{id}/approve")
    public ResponseEntity<String> approveReview(@PathVariable Long id) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(review);

        // Trigger rating recomputation
        ratingService.recalculateRating(review.getTool().getId());

        return ResponseEntity.ok("Review approved and rating updated");
    }

    // PUT /admin/reviews/{id}/reject
    @PutMapping("/{id}/reject")
    public ResponseEntity<String> rejectReview(@PathVariable Long id) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setStatus(ReviewStatus.REJECTED);
        reviewRepository.save(review);

        return ResponseEntity.ok("Review rejected");
    }
}
