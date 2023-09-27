package pl.someday.FishingApp.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static pl.someday.FishingApp.user.PasswordManager.hashPassword;

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
    public String processRegistration(@RequestParam String name, @RequestParam String surname,
                                      @RequestParam Long license, @RequestParam String email, @RequestParam String password) {
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setLicense(license);
            user.setEmail(email);
            user.setPassword(hashPassword(password));
            userRepository.save(user);
        return "redirect:/user/login";
    }


    @GetMapping("/login")
    public String showLoginPage(){
        return"/user/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model){
        User user = userRepository.findByEmail(email);

        if (user != null && BCrypt.checkpw(password, user.getPassword()) ) {
            //TODO
            // zapisac dane do sesji
            return "redirect:/user/dashboard";
        } else {
            return "redirect:/user/login";
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(){
        return "/user/dashboard";
    }
}


