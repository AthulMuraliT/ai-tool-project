package com.aitoolfinder.ai_tools_backend.controller.admin;

import com.aitoolfinder.ai_tools_backend.model.AiTool;
import com.aitoolfinder.ai_tools_backend.service.AiToolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/tools")
public class AdminToolController {

    private final AiToolService aiToolService;

    public AdminToolController(AiToolService aiToolService) {
        this.aiToolService = aiToolService;
    }

    @PostMapping
    public ResponseEntity<AiTool> addTool(@RequestBody AiTool tool) {
        return ResponseEntity.ok(aiToolService.saveTool(tool));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AiTool> updateTool(@PathVariable Long id, @RequestBody AiTool tool) {
        return ResponseEntity.ok(aiToolService.updateTool(id, tool));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTool(@PathVariable Long id) {
        aiToolService.deleteTool(id);
        return ResponseEntity.noContent().build();
    }
}
