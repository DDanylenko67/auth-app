package dev.ddanylenko.authapi.processingLog.service;

import dev.ddanylenko.authapi.processingLog.model.ProcessingLogEntity;
import dev.ddanylenko.authapi.processingLog.repository.ProcessingLogRepository;
import dev.ddanylenko.authapi.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class ProcessingLogService {

    @Value("${app.internal-token}")
    private String internalToken;

    @Value("${app.data-api-url}")
    private String dataApiUrl;

    private final ProcessingLogRepository processingLogRepository;

    private final RestTemplate restTemplate;

    private final UserService userService;

    public ProcessingLogService(ProcessingLogRepository processingLogRepository, UserService userService, RestTemplate restTemplate) {
        this.processingLogRepository = processingLogRepository;
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    public String process(String email, String text){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Internal-Token", internalToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(Map.of("text", text), headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                dataApiUrl,
                request,
                Map.class
        );

        String result = (String) response.getBody().get("result");

        processingLogRepository.save(new ProcessingLogEntity(null, userService.getUserId(email), text, result, LocalDateTime.now()));
        return result;
    }
}
