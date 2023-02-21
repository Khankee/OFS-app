package brunel.ac.uk.ofsapp.controller;

import brunel.ac.uk.ofsapp.dto.UserDto;
import brunel.ac.uk.ofsapp.entity.User;
import brunel.ac.uk.ofsapp.repository.UserRepository;
import brunel.ac.uk.ofsapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    public AuthController(UserService userService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        User existing = userService.findByEmail(userDto.getEmail());

        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
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

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }
}
