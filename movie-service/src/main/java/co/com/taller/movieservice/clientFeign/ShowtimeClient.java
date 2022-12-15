package co.com.taller.movieservice.clientFeign;

import co.com.taller.movieservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "showtime-service", fallback = ShowtimeClientImplHystrixFallBack.class)
public interface ShowtimeClient {

    @GetMapping("/movies_cloud/api/v1/showtimes/movies/{id}")
    Response findByMovies(@PathVariable("id") Long id);

}
