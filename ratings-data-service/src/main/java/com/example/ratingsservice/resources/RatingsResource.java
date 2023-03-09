package com.example.ratingsservice.resources;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.UserRating;
import com.example.ratingsservice.services.RatingsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {
    RatingsService ratingsService;

    public RatingsResource(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @RequestMapping("/{userId}")
    public UserRating getRatingsOfUser(@PathVariable String userId) {
        List<Rating> ratings = ratingsService.getRatings(userId);
        return new UserRating(ratings);
    }
}
