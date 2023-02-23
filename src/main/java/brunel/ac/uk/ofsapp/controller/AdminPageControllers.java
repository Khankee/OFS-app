package brunel.ac.uk.ofsapp.controller;

import brunel.ac.uk.ofsapp.dto.UserDto;
import brunel.ac.uk.ofsapp.entity.User;
import brunel.ac.uk.ofsapp.repository.UserRepository;
import brunel.ac.uk.ofsapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminPageControllers {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminPageControllers(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public String users(Model model, Model adminModel){
        List<UserDto> users = userService.findAllUsers();
        List<User> adminList = userRepository.findAdminUsers();
        User adminUser = null;
        if(!adminList.isEmpty()){
            adminUser = adminList.get(0);
        }
        adminModel.addAttribute("admin" , adminUser);
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/admin-change-password")
    public String adminChangePassword(){
        return "adminChangePassword";
    }

    @PostMapping("/admin-change-password")
    public String processChangePassword(@RequestParam("currentPassword") String currentPassword,
                                        @RequestParam("newPassword") String newPassword,
                                        @RequestParam("confirmPassword") String confirmPassword,
                                        Model model) {

        List<User> adminList = userRepository.findAdminUsers();
        User adminUser = adminList.get(0);

        if (!passwordEncoder.matches(currentPassword, adminUser.getPassword())) {
            model.addAttribute("error", "The current password you entered is incorrect.");
            return "adminChangePassword";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "The new password and confirmed new password do not match.");
            return "adminChangePassword";
        }

        adminUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(adminUser);

        return "redirect:/users";
    }
}
