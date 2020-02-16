package com.eatza.review.service;

import java.util.Optional;

import com.eatza.review.dto.ReviewDto;
import com.eatza.review.dto.ReviewUpdateResponseDto;
import com.eatza.review.exception.ReviewNotFoundException;
import com.eatza.review.model.Review;

public interface ReviewService {

	Review addReview(ReviewDto reviewDto);

	ReviewUpdateResponseDto updateReview(ReviewDto reviewDto) throws ReviewNotFoundException;

	Optional<Review> getReviewById(Long reviewId);

	
}
