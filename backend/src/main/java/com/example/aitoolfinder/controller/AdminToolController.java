package com.example.aitoolfinder.controller;

import com.example.aitoolfinder.dto.CreateToolRequest;
import com.example.aitoolfinder.dto.ToolResponse;
import com.example.aitoolfinder.dto.UpdateToolRequest;
import com.example.aitoolfinder.service.ToolService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/tools")
public class AdminToolController {
    private final ToolService toolService;
    public AdminToolController(ToolService toolService) { this.toolService = toolService; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToolResponse create(@Valid @RequestBody CreateToolRequest req) {
        return toolService.createTool(req);
    }

    @PutMapping("/{id}")
    public ToolResponse update(@PathVariable Long id, @Valid @RequestBody UpdateToolRequest req) {
        return toolService.updateTool(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        toolService.deleteTool(id);
    }
}
