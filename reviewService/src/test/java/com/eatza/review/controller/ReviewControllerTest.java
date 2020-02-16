package com.eatza.review.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eatza.review.dto.ReviewDto;
import com.eatza.review.dto.ReviewUpdateResponseDto;
import com.eatza.review.model.Review;
import com.eatza.review.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ReviewController.class)
public class ReviewControllerTest {

	@MockBean
	ReviewService reviewService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	String jwt = "";

	private static final long EXPIRATIONTIME = 900000;

	@Before
	public void setup() {
		jwt = "Bearer " + Jwts.builder().setSubject("user").claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).compact();
	}

	@Test
	public void addReview() throws Exception {
		ReviewDto requestDto = new ReviewDto();
		requestDto.setCustomerId(1L);
		requestDto.setRating(5);
		requestDto.setRestaurantId(1L);
		requestDto.setReview("Great!");

		when(reviewService.addReview(any(ReviewDto.class))).thenReturn(new Review());
		RequestBuilder request = MockMvcRequestBuilders.post("/review").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((requestDto))).header(HttpHeaders.AUTHORIZATION, jwt);
		mockMvc.perform(request).andExpect(status().is(200)).andExpect(content().string("Review Added successfully"))
				.andReturn();
	}

	@Test
	public void getReviewById() throws Exception {
		Review review = new Review(1L, 1L, 4, "Good");
		Optional<Review> returnedItem = Optional.of(review);
		when(reviewService.getReviewById(anyLong())).thenReturn(returnedItem);
		RequestBuilder request = MockMvcRequestBuilders.get("/review/1?pagenumber=1&pagesize=10").accept(MediaType.ALL)
				.header(HttpHeaders.AUTHORIZATION, jwt);
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}

	@Test
	public void getReviewById_empty() throws Exception {
		when(reviewService.getReviewById(anyLong())).thenReturn(Optional.empty());
		RequestBuilder request = MockMvcRequestBuilders.get("/review/1?pagenumber=1&pagesize=10").accept(MediaType.ALL)
				.header(HttpHeaders.AUTHORIZATION, jwt);
		mockMvc.perform(request).andExpect(status().is(400)).andReturn();
	}

	@Test
	public void updateReview() throws Exception {
		ReviewDto requestDto = new ReviewDto();
		requestDto.setCustomerId(1L);
		requestDto.setRating(5);
		requestDto.setRestaurantId(1L);
		requestDto.setReview("Great!");
		when(reviewService.updateReview(requestDto)).thenReturn(new ReviewUpdateResponseDto());
		RequestBuilder request = MockMvcRequestBuilders.get("/review/1").accept(MediaType.ALL)
				.header(HttpHeaders.AUTHORIZATION, jwt);
		mockMvc.perform(request).andExpect(status().is(400)).andReturn();

	}
}
