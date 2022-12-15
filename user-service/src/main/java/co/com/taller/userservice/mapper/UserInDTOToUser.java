package co.com.taller.userservice.mapper;

import co.com.taller.userservice.DTO.UserInDTO;
import co.com.taller.userservice.persistence.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserInDTOToUser implements IMapper<UserInDTO, User>{

    @Override
    public User map(UserInDTO in) {
        User user = new User();

        user.setName(in.getName());
        user.setLastname(in.getLastname());

        return user;
    }
}
