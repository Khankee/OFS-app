package brunel.ac.uk.ofsapp.service.impl;

import brunel.ac.uk.ofsapp.dto.UserDto;
import brunel.ac.uk.ofsapp.repository.RoleRepository;
import brunel.ac.uk.ofsapp.repository.UserRepository;
import brunel.ac.uk.ofsapp.service.UserService;
import brunel.ac.uk.ofsapp.entity.Role;
import brunel.ac.uk.ofsapp.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (
                (user.getEmail().equals("Arukhan.kenes@gmail.com") && userDto.getPassword().equals("1234567890")) ||
                (user.getEmail().equals("1940930@brunel.ac.uk") && userDto.getPassword().equals("1234567890"))
            ) {
            Role role = roleRepository.findByName("ROLE_ADMIN");
            if(role == null) {
                role = checkRoleExist();
            }
            user.setRoles(List.of(role));
            //user.setEnabled(true);
            userRepository.save(user);
            return;
        }
        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = addRoleUser();
        }
        user.setRoles(List.of(role));
        //user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllUsersWithUserRole();
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    private Role addRoleUser() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}
