package co.com.taller.bookingservice.mapper;

import co.com.taller.bookingservice.DTO.BookingInDTO;
import co.com.taller.bookingservice.DTO.MovieInDTO;
import co.com.taller.bookingservice.persistence.entity.Booking;
import co.com.taller.bookingservice.persistence.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingInDTOToBooking implements IMapper<BookingInDTO, Booking> {

    @Override
    public Booking map(BookingInDTO in) {
        Booking booking = new Booking();
        List<Movie> movies = new ArrayList<>();

        for (MovieInDTO item : in.getMovies()) {
            Movie movie = new Movie();
            movie.setIdMovie(item.getIdMovie());

            movies.add(movie);
        }

        booking.setMovies(movies);
        booking.setUserid(in.getUserid());
        booking.setShowtimeid(in.getShowtimeid());

        return booking;
    }
}
