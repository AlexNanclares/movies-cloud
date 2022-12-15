package co.com.taller.movieservice.service;

import co.com.taller.movieservice.DTO.MovieInDTO;
import co.com.taller.movieservice.persistence.entity.Movie;

import java.util.List;

public interface MovieServices {

    List<Movie> findAllMovies();

    void saveMovie(MovieInDTO movie);

    Movie findMovieById(Long id);

    void deleteMovie(Long id);

}
