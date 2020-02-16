package com.eatza.review.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="reviews")
@Getter @Setter @NoArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Long customerId;
	private Long restaurantId;
	
	private int rating;
	private String review;

	@CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
	public Review(Long customerId, Long restaurantId, int rating, String review) {
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.rating = rating;
		this.review = review;
	}
    
    
}
