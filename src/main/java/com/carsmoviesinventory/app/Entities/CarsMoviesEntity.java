package com.carsmoviesinventory.app.Entities;
import java.util.UUID;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="CarsMovies")
public class CarsMoviesEntity{

    @Id
    private UUID id;
    private String carMovieName;
    private String carMovieYear;
    private Integer duration;
}