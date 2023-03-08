package com.example.ratingsservice.services;

import com.example.ratingsservice.models.MovieRating;
import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.repositories.RatingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingsService {
    private final RatingRepository ratingRepository;

    public RatingsService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    private  Rating generateRating(MovieRating source) {
        try {
            ObjectMapper om = new ObjectMapper();
            Rating target = new Rating();

            String jsonstr = om.writeValueAsString(source);
            om.readerForUpdating(target).readValue(jsonstr);

            return target;
        } catch (Exception ignored) {
            return null;
        }
    }

    public List<Rating> getRatings(String userId) {
        return ratingRepository.findByUserId(userId);
    }
}