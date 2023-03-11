package com.example.topratedservice.services;

import com.example.topratedservice.models.Rating;
import com.example.topratedservice.repositories.RatingsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {
    RatingsRepo repo;

    public RatingsService(RatingsRepo ratingsRepo) {
        this.repo = ratingsRepo;
    }

    public List<String> getTopRatedMovies() {
        return repo.getTopRatedMovies();
    }
}