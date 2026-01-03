package com.example.aitoolfinder.controller;

import com.example.aitoolfinder.dto.ReviewRequest;
import com.example.aitoolfinder.dto.ReviewResponse;
import com.example.aitoolfinder.model.Review;
import com.example.aitoolfinder.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/reviews", "/review"})
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) { this.reviewService = reviewService; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse submit(@Valid @RequestBody ReviewRequest req) {
        return ReviewResponse.from(reviewService.submitReview(req));
    }
}
