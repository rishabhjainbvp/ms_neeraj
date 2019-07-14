package com.example.demo.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.CatalogItem;
import com.example.demo.model.Movie;
import com.example.demo.model.Rating;

@RestController
@RequestMapping(value = "/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

/*	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		return Collections.singletonList(new CatalogItem("Avengers End Game", "Marvel...", 5));
	}*/

/*	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		// get All rated movie ids
		List<Rating> ratings = Arrays.asList(new Rating("1234", 3), new Rating("5678", 4));

		// For each movie id , call movie info service and get details
		return ratings.stream().map(r -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + r.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Marvel...", r.getRating());
		}).collect(Collectors.toList());

		// put them all together

	}*/
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		// get All rated movie ids

		UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/user/" + userId,
				UserRating.class);

		return ratings.getRating().stream().map(r -> {
			// For each movie id , call movie info service and get details
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + r.getMovieId(), Movie.class);
			// put them all together
			return new CatalogItem(movie.getName(), "Marvel...", r.getRating());
		}).collect(Collectors.toList());
	}

}
