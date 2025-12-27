package com.aitoolfinder.ai_tools_backend.repository;

import com.aitoolfinder.ai_tools_backend.entity.AiTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AiToolRepository
        extends JpaRepository<AiTool, Long>,
        JpaSpecificationExecutor<AiTool> {
}
