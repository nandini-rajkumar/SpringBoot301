package com.eatza.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdateResponseDto {

	private Long reviewId;
	private Long customerId;
	private Long restaurantId;
	
	private int rating;
	private String review;
}
