CREATE DATABASE IF NOT EXISTS ai_tool_finder;
USE ai_tool_finder;

CREATE TABLE ai_tools (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    use_case VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    pricing_type ENUM('FREE', 'PAID', 'SUBSCRIPTION') NOT NULL,
    average_rating DECIMAL(2,1) DEFAULT 0.0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_category ON ai_tools(category);
CREATE INDEX idx_pricing ON ai_tools(pricing_type);
CREATE INDEX idx_rating ON ai_tools(average_rating);

CREATE TABLE reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tool_id INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_tool FOREIGN KEY (tool_id)
    REFERENCES ai_tools(id)
    ON DELETE CASCADE
);

CREATE INDEX idx_tool_id ON reviews(tool_id);
CREATE INDEX idx_status ON reviews(status);


ALTER TABLE ai_tools ADD COLUMN use_case VARCHAR(255);


INSERT INTO ai_tools (name, use_case, category, pricing_type, average_rating) VALUES
('ChatGPT', 'Text Generation', 'NLP', 'FREE', 4.5),
('Midjourney', 'Image Generation', 'Computer Vision', 'SUBSCRIPTION', 4.7),
('GitHub Copilot', 'Code Assistance', 'Dev Tools', 'PAID', 4.3),
('Grammarly', 'Writing Assistance', 'Productivity', 'FREE', 4.1);

INSERT INTO reviews (tool_id, rating, comment, status) VALUES
(1, 5, 'Amazing tool for coding and writing', 'APPROVED'),
(1, 4, 'Very helpful but sometimes slow', 'APPROVED'),
(2, 5, 'Best AI image generator', 'APPROVED'),
(3, 4, 'Great for developers', 'PENDING'),
(4, 3, 'Good but limited features in free version', 'APPROVED');
