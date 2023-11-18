package pl.someday.FishingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.someday.FishingApp.dto.UserRegistrationDTO;
import pl.someday.FishingApp.model.User;
import pl.someday.FishingApp.repository.UserRepository;


/**
 * Serwis obsługujący operacje związane z użytkownikami.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Konstruktor klasy UserService.
     *
     * @param userRepository   Repozytorium użytkowników.
     * @param passwordEncoder  Koder hasła.
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Rejestruje nowego użytkownika na podstawie danych z obiektu DTO.
     *
     * @param userDto Obiekt DTO zawierający dane nowego użytkownika.
     * @return Zarejestrowany użytkownik.
     */
    public User registerNewUser(UserRegistrationDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setLicense(userDto.getLicense());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("ROLE_USER"); // default USER role
        user.setActive(true);
        return userRepository.save(user);
    }
}