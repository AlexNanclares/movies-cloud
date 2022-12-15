package co.com.taller.userservice;

import co.com.taller.userservice.mapper.UserInDTOToUser;
import co.com.taller.userservice.persistence.entity.User;
import co.com.taller.userservice.persistence.repository.UserRepository;
import co.com.taller.userservice.service.UserServiceImpl;
import co.com.taller.userservice.service.UserServices;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceMockTest {

    @Mock
    private UserRepository userRepository;
    private UserServices userServices;
    private UserInDTOToUser userInDTOToUser;

    @BeforeEach
    public void begin(){
        MockitoAnnotations.openMocks(this);
        userServices = new UserServiceImpl(userRepository,userInDTOToUser);

        User user = User.builder()
                .id(2L)
                .name("TestName")
                .lastname("TestLastname").build();

        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user));
    }

    @Test
    public void when_findById_return_User(){
        User user = userServices.findUserById(2L);
        Assertions.assertThat(user.getName()).isEqualTo("TestName");
    }

}
