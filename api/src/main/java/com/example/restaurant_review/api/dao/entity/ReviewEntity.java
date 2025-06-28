package com.example.restaurant_review.api.dao.entity;

import com.example.restaurant_review.api.enums.Sentiment;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 1000)
    String content;

    @Enumerated(EnumType.STRING)
    Sentiment sentiment;

    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    RestaurantEntity restaurant;
}
