package com.aitoolfinder.ai_tools_backend.entity;

import jakarta.persistence.*;

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

    @Column(name = "pricing_type")
    private String pricingType;

    @Column(name = "average_rating")
    private Double averageRating;

    // Getters & Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getUseCase() { return useCase; }
    public String getCategory() { return category; }
    public String getPricingType() { return pricingType; }
    public Double getAverageRating() { return averageRating; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUseCase(String useCase) { this.useCase = useCase; }
    public void setCategory(String category) { this.category = category; }
    public void setPricingType(String pricingType) { this.pricingType = pricingType; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
}
