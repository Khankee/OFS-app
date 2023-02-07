/*
package brunel.ac.uk.ofsapp.test;

import brunel.ac.uk.ofsapp.entity.Role;
import brunel.ac.uk.ofsapp.entity.User;
import brunel.ac.uk.ofsapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommandLineAppStartupRunner implements org.springframework.boot.CommandLineRunner {


    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String...args) throws Exception{
        User user = new User();
        user.setEmail("Arukhan.kenes@gmail.com");
        user.setName("Arukhan");
        user.setRole("");
        user.setPassword(passwordEncoder.encode("1234567890"));

        userRepository.save(user);
    }


}
*/
