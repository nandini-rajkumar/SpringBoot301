package com.eatza.review.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eatza.review.dto.ReviewDto;
import com.eatza.review.dto.ReviewUpdateResponseDto;
import com.eatza.review.exception.ReviewNotFoundException;
import com.eatza.review.model.Review;
import com.eatza.review.repository.ReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public Review addReview(ReviewDto reviewDto) {
		log.debug("In addReview method, creating review object to persist");
		Review review = new Review(reviewDto.getCustomerId(), reviewDto.getRestaurantId(), reviewDto.getRating(),
				reviewDto.getReview());
		log.debug("saving review in db");
		Review savedReview = reviewRepository.save(review);

		log.debug("Saved Review to db");
		return savedReview;
	}

	@Override
	public ReviewUpdateResponseDto updateReview(ReviewDto reviewDto) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepository.findById(reviewDto.getReviewId());
		if (review.isPresent()) {
			Review updatedReview = new Review(reviewDto.getCustomerId(), reviewDto.getRestaurantId(),
					reviewDto.getRating(), reviewDto.getReview());
			updatedReview.setId(reviewDto.getReviewId());
			Review savedReview = reviewRepository.save(updatedReview);
			return new ReviewUpdateResponseDto(savedReview.getId(), savedReview.getCustomerId(),
					savedReview.getCustomerId(), savedReview.getRating(), savedReview.getReview());
		} else {
			throw new ReviewNotFoundException("Update Failed, respective review not found");
		}
	}

	@Override
	public Optional<Review> getReviewById(Long reviewId) {
		return reviewRepository.findById(reviewId);
	}

}
