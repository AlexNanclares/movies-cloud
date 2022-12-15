package co.com.taller.showtimeservice.controller;

import co.com.taller.showtimeservice.DTO.ShowtimeInDTO;
import co.com.taller.showtimeservice.DTO.ShowtimeUpdateInDTO;
import co.com.taller.showtimeservice.helpers.Response;
import co.com.taller.showtimeservice.helpers.ResponseBuild;
import co.com.taller.showtimeservice.persistence.entity.Movie;
import co.com.taller.showtimeservice.persistence.entity.Showtime;
import co.com.taller.showtimeservice.service.ShowtimeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeServices showtimeServices;
    private final ResponseBuild build;

    @GetMapping
    public Response findAllShowtimaes(){
        return build.success(showtimeServices.findAllShowtimes());
    }

    @PostMapping
    public Response saveShowtime(@Valid @RequestBody ShowtimeInDTO showtime, BindingResult result){
        if(result.hasErrors()){
            return build.failed(formatMessage(result));
        }

        showtimeServices.saveShowtime(showtime);
        return build.success(showtime);
    }

    @GetMapping("/{id}")
    public Response findShowtimeById(@PathVariable("id") Long id){

        Showtime showtime = showtimeServices.findShowtimeById(id);

        if(showtime == null){
            return build.failed("La programación no existe");
        }

        return build.success(showtime);
    }

    @PutMapping("/{id}")
    public Response updateShowtime(@PathVariable("id") Long id, @RequestBody ShowtimeUpdateInDTO showtime, BindingResult result){
        if(result.hasErrors()){
            return build.failed(formatMessage(result));
        }

        Showtime showtimeResult = showtimeServices.findShowtimeById(id);

        if(showtimeResult == null){
            return build.failed("La programación no existe");
        }

        showtimeServices.updateShowtime(showtime.getDate(), id);
        return build.success();
    }

    @GetMapping("/movies/{id}")
    public Response findByMovies(@PathVariable("id") Long id){

        Movie movie = new Movie();
        movie.setId(id);

        List<Showtime> booking = showtimeServices.findByMovies(movie);
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
