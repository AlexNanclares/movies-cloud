package co.com.taller.showtimeservice.clientFeign;

import co.com.taller.showtimeservice.helpers.Response;
import co.com.taller.showtimeservice.helpers.ResponseBuild;
import co.com.taller.showtimeservice.model.MovieModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MovieClientImplHystrixFallBack implements MovieClient{

    private final ResponseBuild build;

    @Override
    public Response findMovieById(Long id) {
        return build.success(new MovieModel());
    }
}
