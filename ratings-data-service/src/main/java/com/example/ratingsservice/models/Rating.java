package com.example.ratingsservice.models;

import jakarta.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;


@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(appliesTo = "")
@NoArgsConstructor
public class Rating {
    @javax.persistence.Id
    private Long userId;
    private String movieId;
    private int rating;
}
