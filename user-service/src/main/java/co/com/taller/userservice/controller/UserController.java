package co.com.taller.userservice.controller;

import co.com.taller.userservice.DTO.UserInDTO;
import co.com.taller.userservice.clientFeign.BookingClient;
import co.com.taller.userservice.helpers.Response;
import co.com.taller.userservice.helpers.ResponseBuild;
import co.com.taller.userservice.persistence.entity.User;
import co.com.taller.userservice.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userServices;
    private final ResponseBuild build;
    private final BookingClient bookingClient;

    @GetMapping()
    public Response findAllUsers(){
        return build.success(userServices.findAllUsers());
    }

    @GetMapping("/{id}")
    public Response findUserById(@PathVariable("id") Long id){

        User user = userServices.findUserById(id);

        if(user == null){
            return build.failed("El usuario no existe");
        }

        return build.success(user);
    }

    @PostMapping
    public Response saveUser(@Valid @RequestBody UserInDTO user, BindingResult result){
        if(result.hasErrors()){
            return build.failed(formatMessage(result));
        }

        userServices.saveUser(user);
        return build.success(user);
    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable("id") Long id){

        User user = userServices.findUserById(id);

        ModelMapper modelMapper = new ModelMapper();

        List<Object> reservations = (List<Object>) modelMapper
                .map(bookingClient.findBookingByUserId(id).getData(), Object.class);

        if(user == null){ return build.failed("El usuario no existe"); }
        if(reservations.size() != 0){ return build.failed("El usuario no puede tener reservas asociadas"); }

        userServices.deleteUser(id);
        return build.success(user);
    }

    private List<Map<String,String>> formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(error -> {
                    Map<String,String> err = new HashMap<>();
                    err.put(error.getField(),error.getDefaultMessage());
                    return err;
                }).collect(Collectors.toList());
        return errors;
    }

}
