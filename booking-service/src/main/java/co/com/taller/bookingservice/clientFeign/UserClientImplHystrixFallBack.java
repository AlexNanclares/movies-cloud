package co.com.taller.bookingservice.clientFeign;

import co.com.taller.bookingservice.helpers.Response;
import co.com.taller.bookingservice.helpers.ResponseBuild;
import co.com.taller.bookingservice.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserClientImplHystrixFallBack implements UserClient{

    private final ResponseBuild build;

    @Override
    public Response findUserById(Long id) {
        return build.success(new UserModel());
    }
}
