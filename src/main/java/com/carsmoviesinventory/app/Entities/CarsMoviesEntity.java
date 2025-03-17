package com.carsmoviesinventory.app.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CARSMOVIES")
public class CarsMoviesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "carMovieName", nullable = false)
    private String carMovieName;

    @Column(name = "carMovieYear", length = 4, nullable = false)
    private String carMovieYear;

    @Column(name = "duration", nullable = false)
    private Integer duration;
}
