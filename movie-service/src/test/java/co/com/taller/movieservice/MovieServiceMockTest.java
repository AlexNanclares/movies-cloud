package co.com.taller.movieservice;

import co.com.taller.movieservice.mapper.MovieInDTOToMovie;
import co.com.taller.movieservice.persistence.entity.Movie;
import co.com.taller.movieservice.persistence.repository.MovieRepository;
import co.com.taller.movieservice.service.MovieServices;
import co.com.taller.movieservice.service.MovieServicesImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MovieServiceMockTest {

    @Mock
    private MovieRepository movieRepository;
    private MovieServices movieServices;
    private MovieInDTOToMovie movieInDTOToMovie;

    @BeforeEach
    public void begin(){
        MockitoAnnotations.openMocks(this);
        movieServices = new MovieServicesImpl(movieRepository, movieInDTOToMovie);

        Movie movie = Movie.builder()
                .id(6L)
                .title("La mañana fria")
                .director("TestDirector")
                .rating(5).build();

        Mockito.when(movieRepository.findById(6L)).thenReturn(Optional.of(movie));
    }

    @Test
    public void when_findById_return_User(){
        Movie movie = movieServices.findMovieById(6L);
        Assertions.assertThat(movie.getTitle()).isEqualTo("La mañana fria");
    }

}
