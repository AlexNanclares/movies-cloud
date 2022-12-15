package co.com.taller.userservice;

import co.com.taller.userservice.persistence.entity.User;
import co.com.taller.userservice.persistence.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class UserRepositoryMockTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void when_findByUser_return_User(){
        User user = User.builder()
                .name("TestName")
                .lastname("TestLastname").build();

        userRepository.save(user);
        Assertions.assertThat(user.getLastname()).isEqualTo("TestLastname");
    }
}
