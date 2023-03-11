package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.MovieSummary;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/top-rated")
public class TopRatedAgent {
    @Value("${api.key}")
    private String apiKey;
    private final RestTemplate restTemplate;

    public TopRatedAgent(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
