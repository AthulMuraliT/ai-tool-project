# AI Tool Finder (Backend Only)

Spring Boot + MySQL backend for discovering, filtering, reviewing, and moderating AI tools.

## Requirements
- Java 17+
- MySQL 8+
- Maven 3.9+ 

## Setup
1. Create a MySQL database:
   ```sql
   CREATE DATABASE aitoolfinder;
   ```
2. Edit `src/main/resources/application.properties`:
   - `spring.datasource.username` and `spring.datasource.password`
   - DB host/port if not local

3. Build and run:
   ```powershell
   mvn clean package
   mvn spring-boot:run
   ```
   App listens on `http://localhost:8080`.

## Data Model
- Tool: `name`, `useCase`, `category`, `pricingType` (FREE/PAID/SUBSCRIPTION), `averageRating` (derived)
- Review: `toolId`, `rating` (1-5), `comment`, `status` (PENDING/APPROVED/REJECTED)

Only APPROVED reviews affect `averageRating`.

## APIs

### Public
- `GET /api/tools`
  - Filters: `category`, `pricing` or `price`, `rating` (supports ">=4", "<=3", "=5"), `q` (search by name/useCase)
  - Example:
    - `GET /api/tools?category=NLP&rating=>=4&price=FREE`

- `GET /api/tools/{id}`

- `POST /api/reviews`
  - Body:
    ```json
    { "toolId": 1, "rating": 5, "comment": "Great!" }
    ```
  - Returns created review (status PENDING). Triggers rating recompute (approved-only).

### Admin
- `POST /api/admin/tools`
  - Body:
    ```json
    { "name":"NewTool", "useCase":"Text Summarization", "category":"NLP", "pricingType":"FREE" }
    ```
- `PUT /api/admin/tools/{id}`
- `DELETE /api/admin/tools/{id}`
- `PUT /api/admin/reviews/{id}/approve`
- `PUT /api/admin/reviews/{id}/reject`
- `GET /api/admin/reviews?status=PENDING`

Note: This lab does not implement authentication; admin endpoints are separated under `/api/admin/*` for moderation.

## Commands




FILTERING LOGIC
http://localhost:8080/tools?category=NLP&rating>=4&price=Free



ADMIN APPROVING A REVIEW
PUT http://localhost:8080/api/admin/reviews/3/approve



Posting review

POST http://localhost:8080/api/reviews
Content-Type: application/json

{
"toolId": 5,
"rating": 4,
"comment": "Amazing tool!!!"
}




Pending reviews
http://localhost:8080/api/admin/reviews?status=PENDING   
http://localhost:8080/api/admin/reviews?status=APPROVED




REJECT REVIEWS
PUT http://localhost:8080/api/admin/reviews/{reviewId}/reject




add tool

POST http://localhost:8080/api/admin/tools
Content-Type: application/json

{
"name": "ImageCleanerAI",
"useCase": "Image Noise Removal",
"category": "Computer Vision",
"pricingType": "FREE"
}









update tool

PUT http://localhost:8080/api/admin/tools/7
Content-Type: application/json

{
"name": "VisionDetect Pro",
"useCase": "Advanced Object Detection",
"category": "Computer Vision",
"pricingType": "SUBSCRIPTION"
}





delete TOOL

DELETE http://localhost:8080/api/admin/tools/18







## Sample Requests

Fetch all tools:
```powershell
curl http://localhost:8080/api/tools
```

Filter by NLP, free, rating >= 4:
```powershell
curl "http://localhost:8080/api/tools?category=NLP&price=FREE&rating=>=4"
```

Submit a review (tool 1):
```powershell
curl -X POST http://localhost:8080/api/reviews -H "Content-Type: application/json" -d "{\"toolId\":1,\"rating\":5,\"comment\":\"Amazing!\"}"
```

Approve a review (id 10):
```powershell
curl -X PUT http://localhost:8080/api/admin/reviews/10/approve
```

## Team Contribution Notes
- API design: endpoints and DTOs
- Filtering: `ToolSpecifications` with Specification builder
- Rating computation: `ToolService#recomputeAverageRating`
- Moderation: Admin controllers
- Testing & docs: This README and curl examples

## Notes
- `spring.jpa.hibernate.ddl-auto=update` creates tables automatically
- `data.sql` seeds initial tools on first run
- For production, add authentication and pagination
