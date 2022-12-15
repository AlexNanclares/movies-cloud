package co.com.taller.userservice.service;

import co.com.taller.userservice.DTO.UserInDTO;
import co.com.taller.userservice.persistence.entity.User;

import java.util.List;

public interface UserServices {

    User findUserById(Long id);
    List<User> findAllUsers();
    void saveUser(UserInDTO user);
    void deleteUser(Long id);

}
