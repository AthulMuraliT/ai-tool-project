package com.example.aitoolfinder.repository;

import com.example.aitoolfinder.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ToolRepository extends JpaRepository<Tool, Long>, JpaSpecificationExecutor<Tool> {
}
