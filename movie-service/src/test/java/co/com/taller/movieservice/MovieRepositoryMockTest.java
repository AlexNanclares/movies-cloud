package co.com.taller.movieservice;

import co.com.taller.movieservice.persistence.entity.Movie;
import co.com.taller.movieservice.persistence.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MovieRepositoryMockTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void when_findByMovie_return_Movie(){
        Movie movie = Movie.builder()
                .title("la mañana fria")
                .director("N/A")
                .rating(4).build();

        movieRepository.save(movie);
        Assertions.assertThat(movie.getTitle()).isEqualTo("la mañana fria");
    }
}
