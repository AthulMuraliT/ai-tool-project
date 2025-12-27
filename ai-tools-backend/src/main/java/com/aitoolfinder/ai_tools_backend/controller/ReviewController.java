package com.aitoolfinder.ai_tools_backend.controller;

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

    // POST /review
    @PostMapping
    public ResponseEntity<String> submitReview(
            @RequestParam Long toolId,
            @RequestParam Integer rating,
            @RequestParam(required = false) String comment
    ) {
        reviewService.submitReview(toolId, rating, comment);
        return ResponseEntity.ok("Review submitted for approval");
    }
}

