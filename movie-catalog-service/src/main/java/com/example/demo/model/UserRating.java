package com.example.demo.model;

import java.util.List;

public class UserRating {

	private List<Rating> rating;

	public UserRating() {
		super();
	}

	public List<Rating> getRating() {
		return rating;
	}

	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}

}