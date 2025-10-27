package dev.ddanylenko.authapi.processingLog.controller;

import dev.ddanylenko.authapi.processingLog.service.ProcessingLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class ProcessingLogController {

    private final Logger logger = LoggerFactory.getLogger(ProcessingLogController.class);

    private final ProcessingLogService processingLogService;


    public ProcessingLogController(ProcessingLogService processingLogService){
        this.processingLogService = processingLogService;
    }

    @PostMapping("/process")
    public ResponseEntity<?> process(@RequestBody Map<String, String> body, Authentication auth) {
        String text = body.get("text");
        logger.info("Processing text{}", text);
        String result =  processingLogService.process(auth.getName(), text);
        return ResponseEntity.ok(Map.of("result", result));
    }
}
