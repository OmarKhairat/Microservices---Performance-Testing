package com.example.ratingsservice.services;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.repositories.UserRatingRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {
    private final UserRatingRepo userRatingRepo;

    public RatingsService(UserRatingRepo userRatingRepo) {
        this.userRatingRepo = userRatingRepo;
    }

    public List<Rating> getRatings(String userId) {
        return userRatingRepo.findByUserId(userId);
    }
}