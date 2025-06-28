package com.example.restaurant_review.api.service;

import com.example.restaurant_review.api.dto.request.ReviewRequest;

public interface ReviewService {
    void submitReview(ReviewRequest request);
}
