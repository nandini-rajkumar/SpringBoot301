package com.eatza.review.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

	@JsonIgnore
	private Long reviewId;
	private Long customerId;
	private Long restaurantId;
	
	private int rating;
	private String review;
}
