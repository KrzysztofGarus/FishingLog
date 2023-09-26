package pl.someday.FishingApp.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "/user/register";
    }

    @PostMapping("/register")
    public String processRegistration() {
        return "";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return"/user/login";
    }

    @PostMapping("/login")
    public String processLogin(){
        return "";
    }
}


