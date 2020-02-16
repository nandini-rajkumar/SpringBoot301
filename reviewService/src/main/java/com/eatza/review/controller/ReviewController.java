package com.eatza.review.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.review.dto.ReviewDto;
import com.eatza.review.dto.ReviewUpdateResponseDto;
import com.eatza.review.exception.ReviewNotFoundException;
import com.eatza.review.model.Review;
import com.eatza.review.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	@PostMapping("/review")
	public ResponseEntity<String> addReview(@RequestHeader String authorization, @RequestBody ReviewDto reviewDto)
			throws ReviewNotFoundException {
		log.debug("In addReview method, calling the service");
		reviewService.addReview(reviewDto);
		log.debug("Added Review Successfully");
		return ResponseEntity.status(HttpStatus.OK).body("Review Added successfully");
	}

	@PutMapping("/review/{reviewId}")
	public ResponseEntity<ReviewUpdateResponseDto> updateReview(@RequestHeader String authorization,
			@RequestBody ReviewDto reviewDto, @PathVariable Long reviewId) throws ReviewNotFoundException {

		reviewDto.setReviewId(reviewId);
		log.debug(" In review method, calling service");
		ReviewUpdateResponseDto updatedResponse = reviewService.updateReview(reviewDto);
		log.debug("Returning back the object");

		return ResponseEntity.status(HttpStatus.OK).body(updatedResponse);

	}

	@GetMapping("/review/{reviewId}")
	public ResponseEntity<Review> getReviewById(@RequestHeader String authorization, @PathVariable Long reviewId)
			throws ReviewNotFoundException {
		log.debug("In get review by id method, calling service to get Review by ID");
		Optional<Review> review = reviewService.getReviewById(reviewId);
		if (review.isPresent()) {
			log.debug("Got review from service");
			return ResponseEntity.status(HttpStatus.OK).body(review.get());
		} else {
			log.debug("No orders were found");
			throw new ReviewNotFoundException("No result found for specified reviewId");
		}
	}
}
