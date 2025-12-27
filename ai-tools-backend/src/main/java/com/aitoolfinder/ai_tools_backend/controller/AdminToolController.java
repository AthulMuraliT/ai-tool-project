package com.aitoolfinder.ai_tools_backend.controller;

import com.aitoolfinder.ai_tools_backend.entity.AiTool;
import com.aitoolfinder.ai_tools_backend.service.ToolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/tools")
public class AdminToolController {

    private final ToolService toolService;

    public AdminToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    // POST /admin/tools
    @PostMapping
    public ResponseEntity<AiTool> createTool(@RequestBody AiTool tool) {
        return ResponseEntity.ok(toolService.save(tool));
    }

    // PUT /admin/tools/{id}
    @PutMapping("/{id}")
    public ResponseEntity<AiTool> updateTool(
            @PathVariable Long id,
            @RequestBody AiTool updatedTool
    ) {
        AiTool existing = toolService.getToolOrThrow(id);

        existing.setName(updatedTool.getName());
        existing.setUseCase(updatedTool.getUseCase());
        existing.setCategory(updatedTool.getCategory());
        existing.setPricingType(updatedTool.getPricingType());

        return ResponseEntity.ok(toolService.save(existing));
    }

    // DELETE /admin/tools/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTool(@PathVariable Long id) {
        toolService.delete(id);
        return ResponseEntity.ok("Tool deleted successfully");
    }
}
