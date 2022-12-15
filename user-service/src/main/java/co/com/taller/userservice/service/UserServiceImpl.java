package co.com.taller.userservice.service;

import co.com.taller.userservice.DTO.UserInDTO;
import co.com.taller.userservice.clientFeign.BookingClient;
import co.com.taller.userservice.mapper.UserInDTOToUser;
import co.com.taller.userservice.persistence.entity.User;
import co.com.taller.userservice.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices{

    private final UserRepository userRepository;
    private final UserInDTOToUser userInDTOToUser;

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(UserInDTO user) {
        userRepository.save(userInDTOToUser.map(user));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
