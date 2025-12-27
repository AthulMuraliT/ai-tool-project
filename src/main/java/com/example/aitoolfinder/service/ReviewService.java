package com.example.aitoolfinder.service;

import com.example.aitoolfinder.dto.ReviewRequest;
import com.example.aitoolfinder.exception.NotFoundException;
import com.example.aitoolfinder.model.Review;
import com.example.aitoolfinder.model.ReviewStatus;
import com.example.aitoolfinder.model.Tool;
import com.example.aitoolfinder.repository.ReviewRepository;
import com.example.aitoolfinder.repository.ToolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ToolRepository toolRepository;
    private final ToolService toolService;

    public ReviewService(ReviewRepository reviewRepository, ToolRepository toolRepository, ToolService toolService) {
        this.reviewRepository = reviewRepository; this.toolRepository = toolRepository; this.toolService = toolService;
    }

    @Transactional
    public Review submitReview(ReviewRequest req) {
        Tool tool = toolRepository.findById(req.getToolId()).orElseThrow(() -> new NotFoundException("Tool not found: " + req.getToolId()));
        Review review = new Review();
        review.setTool(tool);
        review.setRating(req.getRating());
        review.setComment(req.getComment());
        review.setStatus(ReviewStatus.PENDING);
        Review saved = reviewRepository.save(review);
        // Recompute rating (only approved reviews count, so this may not change)
        toolService.recomputeAverageRating(tool.getId());
        return saved;
    }

    @Transactional
    public Review approveReview(Long reviewId) {
        Review r = reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException("Review not found: " + reviewId));
        r.setStatus(ReviewStatus.APPROVED);
        Review saved = reviewRepository.save(r);
        toolService.recomputeAverageRating(r.getTool().getId());
        return saved;
    }

    @Transactional
    public Review rejectReview(Long reviewId) {
        Review r = reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException("Review not found: " + reviewId));
        r.setStatus(ReviewStatus.REJECTED);
        Review saved = reviewRepository.save(r);
        toolService.recomputeAverageRating(r.getTool().getId());
        return saved;
    }

    public List<Review> findByStatus(ReviewStatus status) {
        return reviewRepository.findAll().stream().filter(r -> r.getStatus() == status).toList();
    }
}
