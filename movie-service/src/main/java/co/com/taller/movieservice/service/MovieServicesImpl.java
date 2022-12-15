package co.com.taller.movieservice.service;

import co.com.taller.movieservice.DTO.MovieInDTO;
import co.com.taller.movieservice.clientFeign.BookingClient;
import co.com.taller.movieservice.mapper.MovieInDTOToMovie;
import co.com.taller.movieservice.persistence.entity.Movie;
import co.com.taller.movieservice.persistence.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServicesImpl implements MovieServices{

    private final MovieRepository movieRepository;
    private final MovieInDTOToMovie movieInDTOToMovie;


    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMovie(MovieInDTO movie) {

        movieRepository.save(movieInDTOToMovie.map(movie));
    }

    @Override
    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMovie(Long id) {




        movieRepository.deleteById(id);
    }
}
