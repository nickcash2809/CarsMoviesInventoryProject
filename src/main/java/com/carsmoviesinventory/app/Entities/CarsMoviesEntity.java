package com.carsmoviesinventory.app.Entities;
import java.util.UUID;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class CarsMoviesEntity{

    private UUID id;
    private String carMovieName;
    private String carMovieYear;
    private Integer duration;
}