package brunel.ac.uk.ofsapp.service;

import brunel.ac.uk.ofsapp.dto.UserDto;
import brunel.ac.uk.ofsapp.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
