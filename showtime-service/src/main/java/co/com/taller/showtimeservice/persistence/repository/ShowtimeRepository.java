package co.com.taller.showtimeservice.persistence.repository;

import co.com.taller.showtimeservice.persistence.entity.Movie;
import co.com.taller.showtimeservice.persistence.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    @Modifying
    @Query(value = "UPDATE showtimes SET date =:date WHERE ID=:id", nativeQuery = true)
    void updateShowtime(@Param("date") LocalDate date, @Param("id") Long id);

    List<Showtime> findByMovies(Movie movie);
}
