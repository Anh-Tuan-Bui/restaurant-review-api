package com.example.restaurant_review.api.service;

import com.example.restaurant_review.api.dto.response.RestaurantResponse;

import java.util.List;

public interface LeaderboardService {
    void updateScore(Long restaurantId, boolean isPositive);
    List<Long> getTopRestaurantIds(int topN);
    List<RestaurantResponse> getLeaderboardRestaurants();
    Double getScore(Long restaurantId);
}
