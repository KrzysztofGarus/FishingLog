package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import pl.someday.FishingApp.dto.UserRegistrationDTO;
import pl.someday.FishingApp.service.UserService;
import javax.validation.Valid;


@Controller
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("userDto", new UserRegistrationDTO());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("userDto") @Valid UserRegistrationDTO userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerNewUser(userDto);
        return "redirect:/login";
    }
}
