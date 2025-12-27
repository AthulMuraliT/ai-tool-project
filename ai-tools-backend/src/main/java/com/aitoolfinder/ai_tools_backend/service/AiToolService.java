package com.aitoolfinder.ai_tools_backend.service;

import com.aitoolfinder.ai_tools_backend.model.AiTool;
import com.aitoolfinder.ai_tools_backend.repository.AiToolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AiToolService {

    private final AiToolRepository aiToolRepository;

    public AiToolService(AiToolRepository aiToolRepository) {
        this.aiToolRepository = aiToolRepository;
    }

    public List<AiTool> getAllTools() {
        return aiToolRepository.findAll();
    }

    public Optional<AiTool> getToolById(Long id) {
        return aiToolRepository.findById(id);
    }
}
