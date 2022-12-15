package co.com.taller.showtimeservice.mapper;

import co.com.taller.showtimeservice.DTO.MovieInDTO;
import co.com.taller.showtimeservice.DTO.ShowtimeInDTO;
import co.com.taller.showtimeservice.persistence.entity.Movie;
import co.com.taller.showtimeservice.persistence.entity.Showtime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShowtimeInDTOToShowtime implements IMapper<ShowtimeInDTO, Showtime> {
    
    @Override
    public Showtime map(ShowtimeInDTO in) {
        Showtime showtime = new Showtime();
        List<Movie> movies = new ArrayList<>();

        for (MovieInDTO item : in.getMovies()) {
            Movie movie = new Movie();
            movie.setIdMovie(item.getIdMovie());

            movies.add(movie);
        }

        showtime.setDate(in.getDate());
        showtime.setMovies(movies);
        
        return showtime;
    }
}
