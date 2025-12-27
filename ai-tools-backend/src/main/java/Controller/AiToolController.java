package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tools")
public class AiToolController {

        @Autowired
        private AIToolService toolService;

        // GET /tools
        @GetMapping
        public ResponseEntity<?> getAllTools() {
            List<AITool> tools = toolService.getAllTools();
            if (tools.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("No AI tools found.");
            }
            return ResponseEntity.ok(tools);
        }

        // GET /tools/{id}
        @GetMapping("/{id}")
        public ResponseEntity<?> getToolById(@PathVariable Long id) {
            Optional<AITool> tool = toolService.getToolById(id);
            if (tool.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("AI tool not found with ID " + id);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("tool", tool.get());
            response.put("averageRating", toolService.getAverageRating(id));

            return ResponseEntity.ok(response);
        }


}
