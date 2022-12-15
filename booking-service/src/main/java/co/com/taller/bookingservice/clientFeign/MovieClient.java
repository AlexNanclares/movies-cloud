package co.com.taller.bookingservice.clientFeign;

import co.com.taller.bookingservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service", fallback = MovieClientImplHystrixFallBack.class)
public interface MovieClient {

    @GetMapping("/movies_cloud/api/v1/movies/{id}")
    Response findMovieById(@PathVariable("id") Long id);

}
