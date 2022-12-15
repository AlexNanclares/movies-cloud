package co.com.taller.movieservice.clientFeign;

import co.com.taller.movieservice.helpers.Response;
import co.com.taller.movieservice.helpers.ResponseBuild;
import co.com.taller.movieservice.persistence.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BookingClientImplHystrixFallBack implements BookingClient {

    private final ResponseBuild build;

    @Override
    public Response findByMovies(Long id) {
        List<Object> emptyList = new ArrayList<Object>();
        return build.success(emptyList);
    }
}
