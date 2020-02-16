package com.eatza.reviewService;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.awt.Menu;
import java.awt.MenuItem;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.eatza.review.dto.ReviewDto;
import com.eatza.review.model.Review;
import com.eatza.review.repository.ReviewRepository;
import com.eatza.review.service.ReviewServiceImpl;

@RunWith(SpringRunner.class)
public class ReviewServiceTest {

	@InjectMocks
	ReviewServiceImpl reviewService;
	
	@Mock ReviewRepository reviewRepository;
	
	@Test
	public void addReview() {
		
		ReviewDto requestDto = new ReviewDto();
		requestDto.setCustomerId(1L);
		requestDto.setRating(5);
		requestDto.setRestaurantId(1L);
		requestDto.setReview("Great!");
		Review review = new Review(requestDto.getCustomerId(), requestDto.getRestaurantId(), requestDto.getRating(), requestDto.getReview());
		when(reviewRepository.save(any(Review.class))).thenReturn(review);
		Review savedItem = reviewService.addReview(requestDto);
		assertNotNull(savedItem);
		
	}
}
