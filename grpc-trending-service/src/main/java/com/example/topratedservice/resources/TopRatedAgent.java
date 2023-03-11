package com.example.topratedservice.resources;

import com.example.topratedservice.models.Movie;
import com.example.topratedservice.models.Rating;
import com.example.topratedservice.services.RatingsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class TopRatedAgent {

    @Value("${api.key}")
    private String apiKey;
    private final RestTemplate restTemplate;
    RatingsService ratingsService;

    public TopRatedAgent(RestTemplate restTemplate, RatingsService ratingsService) {
        this.restTemplate = restTemplate;
        this.ratingsService = ratingsService;
    }

    @RequestMapping("/top-rated")
    public List<Movie> getTopRatedMovies() {
        List<String> movieIds = ratingsService.getTopRatedMovies();
        List<Movie> movies = new ArrayList<>();

        for (String movieId : movieIds) {
            // Get the movie info from TMDB
            final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
            movies.add(restTemplate.getForObject(url, Movie.class));
        }

        return movies;
    }
}
