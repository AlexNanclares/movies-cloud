package co.com.taller.bookingservice.service;

import co.com.taller.bookingservice.DTO.BookingInDTO;
import co.com.taller.bookingservice.clientFeign.MovieClient;
import co.com.taller.bookingservice.clientFeign.ShowtimeClient;
import co.com.taller.bookingservice.clientFeign.UserClient;
import co.com.taller.bookingservice.mapper.BookingInDTOToBooking;
import co.com.taller.bookingservice.model.MovieModel;
import co.com.taller.bookingservice.model.ShowtimeModel;
import co.com.taller.bookingservice.model.UserModel;
import co.com.taller.bookingservice.persistence.entity.Booking;
import co.com.taller.bookingservice.persistence.entity.Movie;
import co.com.taller.bookingservice.persistence.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServicesImpl implements BookingServices{

    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final MovieClient movieClient;
    private final ShowtimeClient showtimeClient;
    private final BookingInDTOToBooking bookingInDTOToBooking;


    @Override
    public List<Booking> findAllBookings() {

        List<Booking> bookings = bookingRepository.findAll();

        for (Booking listBooking : bookings) {
            ModelMapper modelMapper = new ModelMapper();

            List<Movie> moviesResult = listBooking.getMovies().stream().map(movie -> {
                MovieModel movieModel = modelMapper.map(movieClient.findMovieById(movie.getIdMovie()).getData(), MovieModel.class);

                movie.setTitle(movieModel.getTitle());
                movie.setDirector(movieModel.getDirector());
                movie.setRating(movieModel.getRating());

                return movie;
            }).collect(Collectors.toList());

            listBooking.setMovies(moviesResult);

            UserModel userModel = modelMapper
                    .map(userClient.findUserById(listBooking.getUserid()).getData(), UserModel.class);

            listBooking.setUserModel(userModel);

            ShowtimeModel showtimeModel = modelMapper
                    .map(showtimeClient.findShowtimeById(listBooking.getShowtimeid()).getData(), ShowtimeModel.class);

            listBooking.setShowtimeModel(showtimeModel);
        }

        return bookings;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBooking(BookingInDTO booking) {
        bookingRepository.save(bookingInDTOToBooking.map(booking));
    }

    @Override
    public Booking findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);

        if(booking == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();

        List<Movie> movies = booking.getMovies().stream().map(item -> {
            MovieModel movieModel = modelMapper.map(movieClient.findMovieById(item.getIdMovie()).getData(), MovieModel.class);

            item.setTitle(movieModel.getTitle());
            item.setDirector(movieModel.getDirector());
            item.setRating(movieModel.getRating());

            return item;
        }).collect(Collectors.toList());

        booking.setMovies(movies);

        UserModel userModel = modelMapper
                .map(userClient.findUserById(booking.getUserid()).getData(), UserModel.class);

        booking.setUserModel(userModel);

        ShowtimeModel showtimeModel = modelMapper
                .map(showtimeClient.findShowtimeById(booking.getShowtimeid()).getData(), ShowtimeModel.class);

        booking.setShowtimeModel(showtimeModel);

        return booking;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findBookingByUserId(Long userid) {
        return bookingRepository.findByUserid(userid);
    }

    @Override
    public List<Booking> findByMovies(Movie movie) {
        return bookingRepository.findByMovies(movie);
    }
}
