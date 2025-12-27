package com.aitoolfinder.ai_tools_backend.service;

import com.aitoolfinder.ai_tools_backend.entity.AiTool;
import com.aitoolfinder.ai_tools_backend.repository.AiToolRepository;
import com.aitoolfinder.ai_tools_backend.repository.AiToolSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {

    private final AiToolRepository repository;

    public ToolService(AiToolRepository repository) {
        this.repository = repository;
    }

    public List<AiTool> getFilteredTools(String category,
                                         String pricing,
                                         Double rating) {

        Specification<AiTool> spec = Specification
                .where(AiToolSpecification.hasCategory(category))
                .and(AiToolSpecification.hasPricing(pricing))
                .and(AiToolSpecification.hasMinRating(rating));

        return repository.findAll(spec);
    }
}
