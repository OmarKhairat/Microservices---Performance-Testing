package com.example.ratingsservice.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
public class RatingEntity {
    @Id
    private Long userId;
    private String movieId;
    private int rating;
}
