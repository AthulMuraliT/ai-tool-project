package com.example.aitoolfinder.controller;

import com.example.aitoolfinder.dto.ToolResponse;
import com.example.aitoolfinder.service.ToolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/tools", "/tools"})
public class ToolController {
    private final ToolService toolService;

    public ToolController(ToolService toolService) { this.toolService = toolService; }

    @GetMapping
    public List<ToolResponse> list(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "pricing", required = false) String pricing,
            @RequestParam(value = "price", required = false) String priceAlias,
            @RequestParam(value = "rating", required = false) String ratingExpr,
            @RequestParam(value = "q", required = false) String q
    ) {
        String effectivePricing = pricing != null ? pricing : priceAlias; // support price=Free alias
        return toolService.getTools(category, effectivePricing, ratingExpr, q);
    }

    @GetMapping("/{id}")
    public ToolResponse get(@PathVariable Long id) {
        return toolService.getTool(id);
    }
}
