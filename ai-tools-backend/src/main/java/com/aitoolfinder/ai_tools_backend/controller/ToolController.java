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

    // GET /tools
    @GetMapping
    public ResponseEntity<List<AiTool>> getAllTools() {
        List<AiTool> tools = toolService.getAllTools();
        return tools.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(tools);
    }

    // GET /tools/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AiTool> getToolById(@PathVariable Long id) {
        AiTool tool = toolService.getToolById(id);
        return tool == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(tool);

    }
    @PostConstruct
    public void init() {
        System.out.println(">>> ToolController LOADED <<<");
    }

}
