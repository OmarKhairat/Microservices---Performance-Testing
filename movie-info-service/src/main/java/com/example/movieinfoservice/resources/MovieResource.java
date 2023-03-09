package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;
    private RestTemplate restTemplate;
    @Autowired
    SummaryCache summaryCache;

    public MovieResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        // Get the movie info from TMDB
        final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        MovieSummary movieSummary = getAndCacheSummary(movieId, url);

        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }


    private MovieSummary getAndCacheSummary(String movieId, String url) {
        MovieSummary ms = summaryCache.get(movieId);
        if (ms == null) {
            System.out.println("Attempted to cache");
            ms = restTemplate.getForObject(url, MovieSummary.class);
            assert ms != null;
            summaryCache.put(ms);
        }
        return ms;
    }


    @RequestMapping("/put")
    public MovieSummary putIntoDB() {
        System.out.println("Attempted to cache");
        MovieSummary ms = new MovieSummary();
        ms.setId("26363");
        ms.setTitle("Tontonito");
        ms.setOverview("Software good boy");
        summaryCache.put(ms);
        return ms;
    }

    @RequestMapping("/top-rated")
    List<MovieSummary> getTopRated() throws JsonProcessingException {
        final String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey;
        HashMap map = restTemplate.getForObject(url, HashMap.class);
        ArrayList<LinkedHashMap> results = (ArrayList<LinkedHashMap>) map.get("results");
        ArrayList<MovieSummary> topRated = new ArrayList<>();
        for (LinkedHashMap result : results) {
            topRated.add(
                    new MovieSummary(result.get("id").toString(),
                    result.get("original_title").toString(),
                    result.get("overview").toString()));
        }
        return topRated;
    }
}
