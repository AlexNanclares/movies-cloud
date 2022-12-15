package co.com.taller.showtimeservice.service;

import co.com.taller.showtimeservice.DTO.ShowtimeInDTO;
import co.com.taller.showtimeservice.persistence.entity.Movie;
import co.com.taller.showtimeservice.persistence.entity.Showtime;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeServices {
    List<Showtime> findAllShowtimes();
    void saveShowtime(ShowtimeInDTO showtime);
    Showtime findShowtimeById(Long id);
    void updateShowtime(LocalDate date, Long id);
    List<Showtime> findByMovies(Movie movie);
}
