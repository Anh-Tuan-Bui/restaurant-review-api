package com.example.restaurant_review.api.service;

import com.example.restaurant_review.api.dto.request.RestaurantRequest;
import com.example.restaurant_review.api.dto.response.RestaurantResponse;
import com.example.restaurant_review.api.dto.response.ReviewResponse;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest);
    List<ReviewResponse> getReviewsByRestaurantId(Long restaurantId);
}
