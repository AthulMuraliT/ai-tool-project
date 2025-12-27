package com.aitoolfinder.ai_tools_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "test_table")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
