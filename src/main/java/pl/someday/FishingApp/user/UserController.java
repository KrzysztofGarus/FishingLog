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
    public String prepareRegistrationPage() {
        return "";
    }

    @PostMapping("/register")
    public String processRegistrationPage() {
        return "";
    }
}


