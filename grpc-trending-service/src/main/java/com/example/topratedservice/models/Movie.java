package com.example.topratedservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Movie {
    private String movieId;
    private String title;
    private String overview;
    private String overall_rating;
}