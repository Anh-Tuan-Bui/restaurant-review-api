package com.example.restaurant_review.api.dto.response;

import com.example.restaurant_review.api.enums.Sentiment;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponse {
    Long id;
    String content;
    Sentiment sentiment;
    LocalDateTime createdAt;
}
