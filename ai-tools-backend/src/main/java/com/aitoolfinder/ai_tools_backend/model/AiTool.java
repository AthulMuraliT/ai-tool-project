package com.aitoolfinder.ai_tools_backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_tools")
public class AiTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "use_case")
    private String useCase;

    private String category;

    @Enumerated(EnumType.STRING)
    @Column(name = "pricing_type")
    private PricingType pricingType; // Use the external PricingType enum

    @Column(name = "average_rating")
    private BigDecimal averageRating;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Default constructor
    public AiTool() {}

    // Constructor with all fields except ID (auto-generated)
    public AiTool(String name, String useCase, String category, PricingType pricingType, BigDecimal averageRating, LocalDateTime createdAt) {
        this.name = name;
        this.useCase = useCase;
        this.category = category;
        this.pricingType = pricingType;
        this.averageRating = averageRating;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUseCase() { return useCase; }
    public void setUseCase(String useCase) { this.useCase = useCase; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public PricingType getPricingType() { return pricingType; }
    public void setPricingType(PricingType pricingType) { this.pricingType = pricingType; }

    public BigDecimal getAverageRating() { return averageRating; }
    public void setAverageRating(BigDecimal averageRating) { this.averageRating = averageRating; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
