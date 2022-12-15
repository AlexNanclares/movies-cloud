package co.com.taller.bookingservice.controller;

import co.com.taller.bookingservice.DTO.BookingInDTO;
import co.com.taller.bookingservice.helpers.Response;
import co.com.taller.bookingservice.helpers.ResponseBuild;
import co.com.taller.bookingservice.model.UserModel;
import co.com.taller.bookingservice.persistence.entity.Booking;
import co.com.taller.bookingservice.persistence.entity.Movie;
import co.com.taller.bookingservice.service.BookingServices;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingServices bookingServices;
    private final ResponseBuild build;

    @GetMapping
    public Response findAllBookings(){
        return build.success(bookingServices.findAllBookings());
    }

    @PostMapping
    public Response saveBooking(@Valid @RequestBody BookingInDTO booking, BindingResult result){
        if(result.hasErrors()){
            return build.failed(formatMessage(result));
        }

        bookingServices.saveBooking(booking);
        return build.success(booking);
    }

    @GetMapping("/{id}")
    public Response findBookingById(@PathVariable("id") Long id){

        Booking booking = bookingServices.findBookingById(id);

        if(booking == null){
            return build.failed("La reservación no existe");
        }

        return build.success(booking);
    }

    @DeleteMapping("/{id}")
    public Response deleteBooking(@PathVariable("id") Long id){
        Booking booking = bookingServices.findBookingById(id);

        if(booking == null){
            return build.failed("La reservación no existe");
        }

        bookingServices.deleteBooking(id);
        return build.success(booking);
    }

    @GetMapping("/user/{userid}")
    public Response findBookingByUserId(@PathVariable("userid") Long userid){
        List<Booking> booking = bookingServices.findBookingByUserId(userid);

        return build.success(booking);
    }

    @GetMapping("/movies/{id}")
    public Response findByMovies(@PathVariable("id") Long id){
        Movie movie = new Movie();
        movie.setId(id);

        List<Booking> booking = bookingServices.findByMovies(movie);
        return build.success(booking);
    }


    private List<Map<String, String>> formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> err = new HashMap<>();
                    err.put(error.getField(), error.getDefaultMessage());
                    return err;
                }).collect(Collectors.toList());
        return errors;
    }
}
