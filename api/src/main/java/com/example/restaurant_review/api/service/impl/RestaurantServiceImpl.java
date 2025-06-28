package com.example.restaurant_review.api.service.impl;

import com.example.restaurant_review.api.dao.entity.RestaurantEntity;
import com.example.restaurant_review.api.dao.repository.RestaurantRepository;
import com.example.restaurant_review.api.dto.request.RestaurantRequest;
import com.example.restaurant_review.api.dto.response.RestaurantResponse;
import com.example.restaurant_review.api.dto.response.ReviewResponse;
import com.example.restaurant_review.api.exception.NotFoundException;
import com.example.restaurant_review.api.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest) {
        RestaurantEntity savedRestaurant = restaurantRepository.save(
                RestaurantEntity.builder()
                        .name(restaurantRequest.getName())
                        .address(restaurantRequest.getAddress())
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return modelMapper.map(savedRestaurant, RestaurantResponse.class);
    }

    @Override
    public List<ReviewResponse> getReviewsByRestaurantId(Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));

        return restaurant.getReviews().stream()
                .map(review -> modelMapper.map(review, ReviewResponse.class))
                .toList();
    }
}
