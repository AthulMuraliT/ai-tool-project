package com.aitoolfinder.ai_tools_backend.repository;

import com.aitoolfinder.ai_tools_backend.model.AiTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiToolRepository extends JpaRepository<AiTool, Long> {
}
