package co.com.taller.showtimeservice.service;

import co.com.taller.showtimeservice.DTO.ShowtimeInDTO;
import co.com.taller.showtimeservice.clientFeign.MovieClient;
import co.com.taller.showtimeservice.mapper.ShowtimeInDTOToShowtime;
import co.com.taller.showtimeservice.model.MovieModel;
import co.com.taller.showtimeservice.persistence.entity.Movie;
import co.com.taller.showtimeservice.persistence.entity.Showtime;
import co.com.taller.showtimeservice.persistence.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowtimeServicesImpl implements ShowtimeServices{

    private final ShowtimeRepository showtimeRepository;
    private final MovieClient movieClient;
    private final ShowtimeInDTOToShowtime showtimeInDTOToShowtime;

    @Override
    public List<Showtime> findAllShowtimes() {

        List<Showtime> showtimes = showtimeRepository.findAll();

        for (Showtime listShowtime : showtimes) {

            ModelMapper modelMapper = new ModelMapper();

            List<Movie> movies = listShowtime.getMovies().stream().map(item -> {
                MovieModel movieModel = modelMapper.map(movieClient.findMovieById(item.getIdMovie()).getData(), MovieModel.class);

                item.setTitle(movieModel.getTitle());
                item.setDirector(movieModel.getDirector());
                item.setRating(movieModel.getRating());

                return item;
            }).collect(Collectors.toList());

            listShowtime.setMovies(movies);
        }

        return showtimes;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveShowtime(ShowtimeInDTO showtime) {
        showtimeRepository.save(showtimeInDTOToShowtime.map(showtime));
    }

    @Override
    public Showtime findShowtimeById(Long id) {

        Showtime showtime = showtimeRepository.findById(id).orElse(null);

        if(showtime == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();

        List<Movie> movies = showtime.getMovies().stream().map(item -> {
            MovieModel movieModel = modelMapper.map(movieClient.findMovieById(item.getIdMovie()).getData(), MovieModel.class);

            item.setTitle(movieModel.getTitle());
            item.setDirector(movieModel.getDirector());
            item.setRating(movieModel.getRating());

            return item;
        }).collect(Collectors.toList());

        showtime.setMovies(movies);

        return showtime;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateShowtime(LocalDate date, Long id) {
        showtimeRepository.updateShowtime(date, id);
    }

    @Override
    public List<Showtime> findByMovies(Movie movie) {
        return showtimeRepository.findByMovies(movie);
    }

}
