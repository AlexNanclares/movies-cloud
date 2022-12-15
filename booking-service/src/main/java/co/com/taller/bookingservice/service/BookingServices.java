package co.com.taller.bookingservice.service;

import co.com.taller.bookingservice.DTO.BookingInDTO;
import co.com.taller.bookingservice.model.UserModel;
import co.com.taller.bookingservice.persistence.entity.Booking;
import co.com.taller.bookingservice.persistence.entity.Movie;

import java.util.List;

public interface BookingServices {

    List<Booking> findAllBookings();

    void saveBooking(BookingInDTO booking);

    Booking findBookingById(Long id);

    void deleteBooking(Long id);

    List<Booking> findBookingByUserId(Long userid);

    List<Booking> findByMovies(Movie movie);

}
