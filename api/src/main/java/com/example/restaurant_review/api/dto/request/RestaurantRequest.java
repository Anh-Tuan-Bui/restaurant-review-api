package com.example.restaurant_review.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RestaurantRequest {
    @NotBlank(message = "Restaurant's name is required")
    private String name;
    @NotBlank(message = "Restaurant's address is required")
    private String address;
}
