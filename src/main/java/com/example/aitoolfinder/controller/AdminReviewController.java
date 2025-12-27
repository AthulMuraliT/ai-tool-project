package com.example.aitoolfinder.controller;

import com.example.aitoolfinder.dto.ReviewResponse;
import com.example.aitoolfinder.model.Review;
import com.example.aitoolfinder.model.ReviewStatus;
import com.example.aitoolfinder.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reviews")
public class AdminReviewController {
    private final ReviewService reviewService;
    public AdminReviewController(ReviewService reviewService) { this.reviewService = reviewService; }

    @PutMapping("/{id}/approve")
    public ReviewResponse approve(@PathVariable Long id) {
        return ReviewResponse.from(reviewService.approveReview(id));
    }

    @PutMapping("/{id}/reject")
    public ReviewResponse reject(@PathVariable Long id) {
        return ReviewResponse.from(reviewService.rejectReview(id));
    }

    @GetMapping
    public List<ReviewResponse> listByStatus(@RequestParam(name = "status", defaultValue = "PENDING") ReviewStatus status) {
        return reviewService.findByStatus(status).stream().map(ReviewResponse::from).toList();
    }
}
