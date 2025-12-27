package com.aitoolfinder.ai_tools_backend.controller;

import com.aitoolfinder.ai_tools_backend.model.AiTool;
import com.aitoolfinder.ai_tools_backend.model.PricingType;
import com.aitoolfinder.ai_tools_backend.service.AiToolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/tools")
public class AiToolController {

    private final AiToolService aiToolService;

    public AiToolController(AiToolService aiToolService) {
        this.aiToolService = aiToolService;
    }

    /**
     * GET /tools
     * GET /tools?category=&pricing=&rating=
     */
    @GetMapping
    public ResponseEntity<List<AiTool>> getTools(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) PricingType pricing,
            @RequestParam(required = false) BigDecimal rating
    ) {

        List<AiTool> tools = aiToolService.getFilteredTools(category, pricing, rating);

        if (tools.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tools);
    }

    /**
     * GET /tools/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<AiTool> getToolById(@PathVariable Long id) {
        return aiToolService.getToolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}