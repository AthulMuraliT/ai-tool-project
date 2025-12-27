package com.aitoolfinder.ai_tools_backend.controller;

import com.aitoolfinder.ai_tools_backend.model.Review;
import com.aitoolfinder.ai_tools_backend.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * POST /review
     */
    @PostMapping
    public ResponseEntity<Review> submitReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.submitReview(review));
    }

}
