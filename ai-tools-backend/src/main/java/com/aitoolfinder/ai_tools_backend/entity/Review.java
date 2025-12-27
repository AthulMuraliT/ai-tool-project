package com.aitoolfinder.ai_tools_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tool_id", nullable = false)
    private AiTool tool;

    private Integer rating;

    private String comment;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    // getters & setters
    public Long getId() { return id; }
    public AiTool getTool() { return tool; }
    public Integer getRating() { return rating; }
    public String getComment() { return comment; }
    public ReviewStatus getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setTool(AiTool tool) { this.tool = tool; }
    public void setRating(Integer rating) { this.rating = rating; }
    public void setComment(String comment) { this.comment = comment; }
    public void setStatus(ReviewStatus status) { this.status = status; }
}
