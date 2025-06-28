package com.example.restaurant_review.api.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantResponse {
    Long id;
    String name;
    String address;
    Double totalScore = 0.0;
    Integer reviewCount = 0;
}
