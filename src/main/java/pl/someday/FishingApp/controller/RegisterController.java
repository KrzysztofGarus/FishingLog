package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.someday.FishingApp.model.user.User;
import pl.someday.FishingApp.model.user.UserRepository;

@Controller public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String name, @RequestParam String surname,
                                      @RequestParam Long license, @RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setLicense(license);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER"); // default USER role
        userRepository.save(user);
        return "redirect:/login";
    }
}
