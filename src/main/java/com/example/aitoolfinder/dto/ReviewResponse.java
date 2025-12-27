package com.example.aitoolfinder.dto;

import com.example.aitoolfinder.model.Review;
import com.example.aitoolfinder.model.ReviewStatus;

import java.time.Instant;

public class ReviewResponse {
    private Long id;
    private Long toolId;
    private Integer rating;
    private String comment;
    private ReviewStatus status;
    private Instant createdAt;

    public ReviewResponse() {}

    public ReviewResponse(Long id, Long toolId, Integer rating, String comment, ReviewStatus status, Instant createdAt) {
        this.id = id; this.toolId = toolId; this.rating = rating; this.comment = comment; this.status = status; this.createdAt = createdAt;
    }

    public static ReviewResponse from(Review r) {
        return new ReviewResponse(r.getId(), r.getTool().getId(), r.getRating(), r.getComment(), r.getStatus(), r.getCreatedAt());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getToolId() { return toolId; }
    public void setToolId(Long toolId) { this.toolId = toolId; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public ReviewStatus getStatus() { return status; }
    public void setStatus(ReviewStatus status) { this.status = status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
