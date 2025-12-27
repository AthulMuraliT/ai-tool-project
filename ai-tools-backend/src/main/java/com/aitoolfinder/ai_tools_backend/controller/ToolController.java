package com.aitoolfinder.ai_tools_backend.controller;

import com.aitoolfinder.ai_tools_backend.entity.AiTool;
import com.aitoolfinder.ai_tools_backend.service.ToolService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping
    public ResponseEntity<?> getFilteredTools(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String pricing,
            @RequestParam(required = false) Double rating
    ) {

        List<AiTool> tools =
                toolService.getFilteredTools(category, pricing, rating);

        if (tools.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tools);
    }


}
