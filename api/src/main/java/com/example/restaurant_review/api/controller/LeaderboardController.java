package com.example.restaurant_review.api.controller;

import com.example.restaurant_review.api.dto.response.RestaurantResponse;
import com.example.restaurant_review.api.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class LeaderboardController {
    private final LeaderboardService leaderboardService;

    @GetMapping("/leaderboard")
    public ResponseEntity<List<RestaurantResponse>> getLeaderboard() {
        return ResponseEntity.ok(leaderboardService.getLeaderboardRestaurants());
    }
}
