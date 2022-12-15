package co.com.taller.bookingservice.persistence.repository;

import co.com.taller.bookingservice.model.UserModel;
import co.com.taller.bookingservice.persistence.entity.Booking;
import co.com.taller.bookingservice.persistence.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserid(Long userid);

    List<Booking> findByMovies(Movie movie);
}
