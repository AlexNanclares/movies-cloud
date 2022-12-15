package co.com.taller.userservice.clientFeign;

import co.com.taller.userservice.helpers.Response;
import co.com.taller.userservice.helpers.ResponseBuild;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BookingClientImplHystrixFallBack implements BookingClient {

    private final ResponseBuild build;

    @Override
    public Response findBookingByUserId(Long userid) {
        List<Object> emptyList = new ArrayList<Object>();
        return build.success(emptyList);
    }
}
