package com.example.aitoolfinder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateToolRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String useCase;
    @NotBlank
    private String category;
    @NotBlank
    private String pricingType; // FREE, PAID, SUBSCRIPTION

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUseCase() { return useCase; }
    public void setUseCase(String useCase) { this.useCase = useCase; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getPricingType() { return pricingType; }
    public void setPricingType(String pricingType) { this.pricingType = pricingType; }
}
