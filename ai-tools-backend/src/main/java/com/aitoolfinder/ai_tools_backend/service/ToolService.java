package com.aitoolfinder.ai_tools_backend.service;

import com.aitoolfinder.ai_tools_backend.entity.AiTool;
import com.aitoolfinder.ai_tools_backend.repository.AiToolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {

    private final AiToolRepository toolRepository;

    public ToolService(AiToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    // Used by ReviewService & RatingService
    public AiTool getToolOrThrow(Long toolId) {
        return toolRepository.findById(toolId)
                .orElseThrow(() -> new RuntimeException("Tool not found"));
    }

    // Used by ToolController (GET /tools/{id})
    public AiTool getToolById(Long id) {
        return toolRepository.findById(id).orElse(null);
    }

    // Used by ToolController (GET /tools)
    public List<AiTool> getAllTools() {
        return toolRepository.findAll();
    }

    // Used by RatingService
    public AiTool save(AiTool tool) {
        return toolRepository.save(tool);
    }
    public void delete(Long id) {
        toolRepository.deleteById(id);
    }

}
