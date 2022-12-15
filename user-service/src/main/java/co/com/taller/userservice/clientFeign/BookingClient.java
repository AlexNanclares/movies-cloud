package co.com.taller.userservice.clientFeign;

import co.com.taller.userservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service")
public interface BookingClient {

    @GetMapping("/movies_cloud/api/v1/bookings/user/{userid}")
    Response findBookingByUserId(@PathVariable("userid") Long userid);

}
