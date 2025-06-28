package com.example.restaurant_review.api.controller;

import com.example.restaurant_review.api.dto.request.RestaurantRequest;
import com.example.restaurant_review.api.dto.response.RestaurantResponse;
import com.example.restaurant_review.api.dto.response.ReviewResponse;
import com.example.restaurant_review.api.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody @Valid RestaurantRequest request) {
        return ResponseEntity.ok(restaurantService.createRestaurant(request));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewResponse>> getRestaurantReviews(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getReviewsByRestaurantId(id));
    }
}
