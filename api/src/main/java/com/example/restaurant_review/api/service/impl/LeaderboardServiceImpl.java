package com.example.restaurant_review.api.service.impl;

import com.example.restaurant_review.api.dao.repository.RestaurantRepository;
import com.example.restaurant_review.api.dto.response.RestaurantResponse;
import com.example.restaurant_review.api.service.LeaderboardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LeaderboardServiceImpl implements LeaderboardService {
    static String KEY = "restaurant_leaderboard";
    StringRedisTemplate stringRedisTemplate;
    RestaurantRepository restaurantRepository;
    ModelMapper modelMapper;

    @Override
    public void updateScore(Long restaurantId, boolean isPositive) {
        double delta = isPositive ? 1.0 : -1.0;
        stringRedisTemplate.opsForZSet().incrementScore(KEY, restaurantId.toString(), delta);
    }

    @Override
    public List<Long> getTopRestaurantIds(int topN) {
        return Objects.requireNonNull(stringRedisTemplate
                        .opsForZSet()
                        .reverseRange(KEY, 0, topN - 1)
                ).stream()
                .map(Long::valueOf)
                .toList();
    }

    @Override
    public List<RestaurantResponse> getLeaderboardRestaurants() {
        List<Long> topRestaurantIds = getTopRestaurantIds(10);

        return topRestaurantIds.stream()
                .map(id -> restaurantRepository.findById(id)
                        .map(restaurantEntity -> {
                            RestaurantResponse response = modelMapper.map(restaurantEntity, RestaurantResponse.class);
                            response.setTotalScore(getScore(id));
                            response.setReviewCount(restaurantEntity.getReviews().size());

                            return response;
                        }).orElse(null)
                ).filter(Objects::nonNull)
                .toList();
    }

    @Override
    public Double getScore(Long restaurantId) {
        Double score = stringRedisTemplate.opsForZSet().score(KEY, restaurantId.toString());
        return score != null ? score : 0.0;
    }
}