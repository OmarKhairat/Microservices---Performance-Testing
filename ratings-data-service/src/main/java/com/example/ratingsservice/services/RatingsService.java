package com.example.ratingsservice.services;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.RatingEntity;
import com.example.ratingsservice.repositories.RatingRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingsService {

//    private final RatingRepo ratingRepo;
//
//    public RatingsService(RatingRepo ratingRepo) {
//        this.ratingRepo = ratingRepo;
//    }

    private static Rating generateRating(RatingEntity source) {
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

//    public List<Rating> getRatings(String userId) {
////        return ratingRepo.findByUserId(userId);
//    }
}