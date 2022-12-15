package co.com.taller.movieservice.controller;

import co.com.taller.movieservice.DTO.MovieInDTO;
import co.com.taller.movieservice.clientFeign.BookingClient;
import co.com.taller.movieservice.clientFeign.ShowtimeClient;
import co.com.taller.movieservice.helpers.Response;
import co.com.taller.movieservice.helpers.ResponseBuild;
import co.com.taller.movieservice.persistence.entity.Movie;
import co.com.taller.movieservice.service.MovieServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieServices movieServices;
    private final ResponseBuild build;
    private final BookingClient bookingClient;
    private final ShowtimeClient showtimeClient;

    @GetMapping
    public Response findAllMovies(){
        return build.success(movieServices.findAllMovies());
    }

    @PostMapping
    public Response saveMovie(@Valid @RequestBody MovieInDTO movie, BindingResult result){
        if(result.hasErrors()){
            return build.failed(formatMessage(result));
        }

        if(movie.getRating() < 1 || movie.getRating() > 5){
            return build.failed("El numero de rating debe ser entre el 1 y el 5");
        }

        movieServices.saveMovie(movie);
        return build.success(movie);
    }

    @GetMapping("/{id}")
    public Response findMovieById(@PathVariable("id") Long id){
        Movie movie = movieServices.findMovieById(id);

        if(movie == null){
            return build.failed("La pelicula no existe");
        }

        return build.success(movie);
    }

    @DeleteMapping("/{id}")
    public Response deleteMovie(@PathVariable("id") Long id){
        Movie movie = movieServices.findMovieById(id);

        if(movie == null){
            return build.failed("La pelicula no existe");
        }

        ModelMapper modelMapper = new ModelMapper();

        List<Object> moviesFromBookings = (List<Object>) modelMapper
                .map(bookingClient.findByMovies(id).getData(), Object.class);

        List<Object> moviesFromShowtimes = (List<Object>) modelMapper
                .map(showtimeClient.findByMovies(id).getData(), Object.class);

        if(moviesFromBookings.size() != 0){ return build.failed("La pelicula no puede tener reservas asociadas"); }
        if(moviesFromShowtimes.size() != 0){ return build.failed("La pelicula no puede tener programaciones asociadas"); }

        movieServices.deleteMovie(id);
        return build.success(movie);
    }

    private List<Map<String,String>> formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(error -> {
                    Map<String,String> err = new HashMap<>();
                    err.put(error.getField(),error.getDefaultMessage());
                    return err;
                }).collect(Collectors.toList());
        return errors;
    }

}
