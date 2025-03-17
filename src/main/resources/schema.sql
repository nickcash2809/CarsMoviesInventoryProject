CREATE TABLE IF NOT EXISTS CARSMOVIES (
                                          id UUID PRIMARY KEY DEFAULT RANDOM_UUID(),
                                          "carMovieName" VARCHAR(255) NOT NULL,
                                          "carMovieYear" VARCHAR(4) NOT NULL,
                                          "duration" INT NOT NULL
);