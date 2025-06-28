package com.example.restaurant_review.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewRequest {
    @NotNull(message = "Restaurant's id is required")
    private Long restaurantId;

    @NotBlank(message = "Content is required")
    private String content;
}
