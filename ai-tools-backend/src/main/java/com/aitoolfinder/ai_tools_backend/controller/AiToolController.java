package com.aitoolfinder.ai_tools_backend.controller;

import com.aitoolfinder.ai_tools_backend.model.AiTool;
import com.aitoolfinder.ai_tools_backend.service.AiToolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tools")
public class AiToolController {

    private final AiToolService aiToolService;

    public AiToolController(AiToolService aiToolService) {
        this.aiToolService = aiToolService;
    }

    @GetMapping
    public ResponseEntity<List<AiTool>> getAllTools() {
        List<AiTool> tools = aiToolService.getAllTools();
        if (tools.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tools);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiTool> getToolById(@PathVariable Long id) {
        return aiToolService.getToolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
