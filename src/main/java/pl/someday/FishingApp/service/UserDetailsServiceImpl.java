package pl.someday.FishingApp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.someday.FishingApp.model.User;
import pl.someday.FishingApp.repository.UserRepository;

/**
 * Implementacja interfejsu UserDetailsService obsługująca pobieranie szczegółów użytkownika.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * Konstruktor klasy UserDetailsServiceImpl.
     *
     * @param userRepository Repozytorium użytkowników.
     */
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Pobiera szczegóły użytkownika na podstawie nazwy użytkownika.
     *
     * @param username Nazwa użytkownika.
     * @return Szczegóły użytkownika (implementacja UserDetails).
     * @throws UsernameNotFoundException Wyjątek w przypadku, gdy nie znaleziono użytkownika o podanej nazwie.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No username " + username + " found");
        }
        return user;
    }

}
