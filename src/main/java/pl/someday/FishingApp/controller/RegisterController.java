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


/**
 * Kontroler obsługujący proces rejestracji użytkowników.
 * Klasa ta jest odpowiedzialna za obsługę żądań związanych z rejestracją nowych użytkowników,
 * prezentację widoku rejestracji oraz przetwarzanie danych wprowadzonych przez użytkownika
 * podczas rejestracji.
 */
@Controller
public class RegisterController {

    private final UserService userService;

    /**
     * Konstruktor klasy `RegisterController`.
     *
     * @param userService Serwis obsługujący operacje związane z użytkownikami.
     */
    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia strony rejestracji.
     *
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku strony rejestracji.
     */
    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("userDto", new UserRegistrationDTO());
        return "register";
    }

    /**
     * Metoda obsługująca proces rejestracji nowego użytkownika.
     *
     * @param userDto Obiekt DTO (Data Transfer Object) zawierający dane nowego użytkownika.
     * @param result Obiekt `BindingResult` do przechwytywania błędów walidacji.
     * @return Nazwa widoku strony rejestracji w przypadku błędów, w przeciwnym razie przekierowanie na stronę logowania.
     */
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("userDto") @Valid UserRegistrationDTO userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerNewUser(userDto);
        return "redirect:/login";
    }
}
