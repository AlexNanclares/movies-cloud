package co.com.taller.bookingservice.clientFeign;

import co.com.taller.bookingservice.helpers.Response;
import co.com.taller.bookingservice.helpers.ResponseBuild;
import co.com.taller.bookingservice.model.MovieModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MovieClientImplHystrixFallBack implements MovieClient {

    private final ResponseBuild build;

    @Override
    public Response findMovieById(Long id) {
        return build.success(new MovieModel());
    }
}
