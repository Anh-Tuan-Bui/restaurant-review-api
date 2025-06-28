package com.example.restaurant_review.api.service.impl;

import com.example.restaurant_review.api.dao.entity.RestaurantEntity;
import com.example.restaurant_review.api.dao.entity.ReviewEntity;
import com.example.restaurant_review.api.dao.repository.RestaurantRepository;
import com.example.restaurant_review.api.dao.repository.ReviewRepository;
import com.example.restaurant_review.api.dto.request.ReviewRequest;
import com.example.restaurant_review.api.enums.Sentiment;
import com.example.restaurant_review.api.exception.NotFoundException;
import com.example.restaurant_review.api.service.LeaderboardService;
import com.example.restaurant_review.api.service.NlpService;
import com.example.restaurant_review.api.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewServiceImpl implements ReviewService {
    RestaurantRepository restaurantRepository;
    ReviewRepository reviewRepository;
    LeaderboardService leaderboardService;
    NlpService nlpService;

    @Override
    @Transactional
    public void submitReview(ReviewRequest request) {
        RestaurantEntity restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));

        String sentimentResult = nlpService.analyzeSentiment(request.getContent());
        Sentiment sentiment = Sentiment.valueOf(sentimentResult);

        ReviewEntity review = ReviewEntity.builder()
                .restaurant(restaurant)
                .content(request.getContent())
                .sentiment(sentiment)
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);
        leaderboardService.updateScore(restaurant.getId(), sentiment == Sentiment.POSITIVE);
    }
}
