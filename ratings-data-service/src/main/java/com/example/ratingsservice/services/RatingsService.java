package com.example.ratingsservice.services;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.repositories.RatingsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {
    RatingsRepo repo;

    public RatingsService(RatingsRepo ratingsRepo) {
        this.repo = ratingsRepo;
    }

    public List<Rating> getRatings(String userId) {
        return repo.getRatingsByUserId(userId);
    }
}