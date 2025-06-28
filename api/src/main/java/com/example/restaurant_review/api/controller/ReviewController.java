package com.example.restaurant_review.api.controller;

import com.example.restaurant_review.api.dto.request.ReviewRequest;
import com.example.restaurant_review.api.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> submitReview(@RequestBody @Valid ReviewRequest request) {
        reviewService.submitReview(request);

        return ResponseEntity.ok("Review submitted successfully.");
    }
}
