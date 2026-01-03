package com.example.aitoolfinder.dto;

import com.example.aitoolfinder.model.PricingType;

public class ToolResponse {
    private Long id;
    private String name;
    private String useCase;
    private String category;
    private PricingType pricingType;
    private Double averageRating;

    public ToolResponse() {}

    public ToolResponse(Long id, String name, String useCase, String category, PricingType pricingType, Double averageRating) {
        this.id = id; this.name = name; this.useCase = useCase; this.category = category; this.pricingType = pricingType; this.averageRating = averageRating;
    }

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
    public Double getAverageRating() { return averageRating; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
}
