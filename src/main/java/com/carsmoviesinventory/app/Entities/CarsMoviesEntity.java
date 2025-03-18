package com.carsmoviesinventory.app.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarsMoviesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("carMovieName")
    private String carMovieName;

    @JsonProperty("carMovieYear")
    private String carMovieYear;

    @JsonProperty("duration")
    private Integer duration;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Override
    public String toString() {
        return "CarsMoviesEntity{" +
                "id=" + id +
                ", carMovieName='" + carMovieName + '\'' +
                ", carMovieYear='" + carMovieYear + '\'' +
                ", duration=" + duration +
                '}';
    }

}
