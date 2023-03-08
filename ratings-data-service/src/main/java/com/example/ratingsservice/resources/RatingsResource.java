package com.example.ratingsservice.resources;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.UserRating;
//import com.example.ratingsservice.services.RatingsService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

//    private final RatingsService ratingsService;

//    public RatingsResource(RatingsService ratingsService) {
//        this.ratingsService = ratingsService;
//    }

    @RequestMapping("/{userId}")
    public ResponseEntity<Object> getRatingsOfUser(@PathVariable String userId) {
        try {
//            List<Rating> ratings = ratingsService.getRatings(userId);
            List<Rating> ratings = new ArrayList<>();
            return (!ratings.isEmpty())
                    ? new ResponseEntity<>(new UserRating(ratings), HttpStatus.OK)
                    : new ResponseEntity<>(new UserRating(ratings), HttpStatus.NOT_FOUND);
        } catch (Exception ignored) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
