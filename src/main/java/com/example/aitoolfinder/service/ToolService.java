package com.example.aitoolfinder.service;

import com.example.aitoolfinder.dto.CreateToolRequest;
import com.example.aitoolfinder.dto.ToolResponse;
import com.example.aitoolfinder.dto.UpdateToolRequest;
import com.example.aitoolfinder.exception.NotFoundException;
import com.example.aitoolfinder.model.PricingType;
import com.example.aitoolfinder.model.Review;
import com.example.aitoolfinder.model.ReviewStatus;
import com.example.aitoolfinder.model.Tool;
import com.example.aitoolfinder.repository.ReviewRepository;
import com.example.aitoolfinder.repository.ToolRepository;
import com.example.aitoolfinder.spec.ToolSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolService {
    private final ToolRepository toolRepository;
    private final ReviewRepository reviewRepository;

    public ToolService(ToolRepository toolRepository, ReviewRepository reviewRepository) {
        this.toolRepository = toolRepository; this.reviewRepository = reviewRepository;
    }

    public List<ToolResponse> getTools(String category, String pricing, String ratingExpr, String q) {
        Specification<Tool> spec = ToolSpecifications.build(category, pricing, ratingExpr, q);
        return toolRepository.findAll(spec).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ToolResponse getTool(Long id) {
        Tool t = toolRepository.findById(id).orElseThrow(() -> new NotFoundException("Tool not found: " + id));
        return toResponse(t);
    }

    @Transactional
    public ToolResponse createTool(CreateToolRequest req) {
        Tool t = new Tool();
        t.setName(req.getName());
        t.setUseCase(req.getUseCase());
        t.setCategory(req.getCategory());
        t.setPricingType(parsePricing(req.getPricingType()));
        t.setAverageRating(0.0);
        t = toolRepository.save(t);
        return toResponse(t);
    }

    @Transactional
    public ToolResponse updateTool(Long id, UpdateToolRequest req) {
        Tool t = toolRepository.findById(id).orElseThrow(() -> new NotFoundException("Tool not found: " + id));
        t.setName(req.getName());
        t.setUseCase(req.getUseCase());
        t.setCategory(req.getCategory());
        t.setPricingType(parsePricing(req.getPricingType()));
        return toResponse(toolRepository.save(t));
    }

    @Transactional
    public void deleteTool(Long id) {
        Tool t = toolRepository.findById(id).orElseThrow(() -> new NotFoundException("Tool not found: " + id));
        toolRepository.delete(t);
    }

    @Transactional
    public void recomputeAverageRating(Long toolId) {
        Tool t = toolRepository.findById(toolId).orElseThrow(() -> new NotFoundException("Tool not found: " + toolId));
        List<Review> approved = reviewRepository.findByToolAndStatus(t, ReviewStatus.APPROVED);
        double avg = 0.0;
        if (!approved.isEmpty()) {
            avg = approved.stream().mapToInt(Review::getRating).average().orElse(0.0);
        }
        t.setAverageRating(Math.round(avg * 100.0) / 100.0);
        toolRepository.save(t);
    }

    private PricingType parsePricing(String s) {
        try { return PricingType.valueOf(s.trim().toUpperCase()); } catch (Exception e) { throw new IllegalArgumentException("Invalid pricingType: " + s); }
    }

    private ToolResponse toResponse(Tool t) {
        return new ToolResponse(t.getId(), t.getName(), t.getUseCase(), t.getCategory(), t.getPricingType(), t.getAverageRating());
    }
}
