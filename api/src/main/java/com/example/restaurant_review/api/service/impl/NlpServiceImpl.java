package com.example.restaurant_review.api.service.impl;

import com.example.restaurant_review.api.service.NlpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NlpServiceImpl implements NlpService {
    private final RestTemplate restTemplate;

    @Override
    public String analyzeSentiment(String content) {
        String url = "http://127.0.0.1:5050/analyze";

        Map<String, String> payload = new HashMap<>();
        payload.put("content", content);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

            return Objects.requireNonNull(response.getBody()).get("sentiment").toString();
        } catch (Exception e) {
            e.printStackTrace();

            return "NEUTRAL";
        }
    }
}
