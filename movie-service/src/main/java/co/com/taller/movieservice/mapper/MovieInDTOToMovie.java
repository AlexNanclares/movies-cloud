package co.com.taller.movieservice.mapper;

import co.com.taller.movieservice.DTO.MovieInDTO;
import co.com.taller.movieservice.persistence.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieInDTOToMovie implements IMapper<MovieInDTO, Movie>{


    @Override
    public Movie map(MovieInDTO in) {

        Movie movie = new Movie();

        movie.setTitle(in.getTitle());
        movie.setDirector(in.getDirector());
        movie.setRating(in.getRating());

        return movie;
    }
}
