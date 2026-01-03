package com.example.aitoolfinder.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReviewRequest {
    @NotNull
    private Long toolId;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    private String comment;

    public Long getToolId() { return toolId; }
    public void setToolId(Long toolId) { this.toolId = toolId; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
