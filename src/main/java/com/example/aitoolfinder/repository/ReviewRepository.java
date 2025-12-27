package com.example.aitoolfinder.repository;

import com.example.aitoolfinder.model.Review;
import com.example.aitoolfinder.model.ReviewStatus;
import com.example.aitoolfinder.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByToolAndStatus(Tool tool, ReviewStatus status);
}
