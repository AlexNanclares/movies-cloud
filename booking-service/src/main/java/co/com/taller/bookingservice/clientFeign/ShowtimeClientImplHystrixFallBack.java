package co.com.taller.bookingservice.clientFeign;

import co.com.taller.bookingservice.helpers.Response;
import co.com.taller.bookingservice.helpers.ResponseBuild;
import co.com.taller.bookingservice.model.ShowtimeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ShowtimeClientImplHystrixFallBack implements ShowtimeClient {

    private final ResponseBuild build;

    @Override
    public Response findShowtimeById(Long id) {
        return build.success(new ShowtimeModel());
    }
}
