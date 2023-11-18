package pl.someday.FishingApp.service;

import org.springframework.stereotype.Service;
import pl.someday.FishingApp.model.CustomUser;
import pl.someday.FishingApp.model.User;
import pl.someday.FishingApp.repository.UserRepository;

@Service
public class CustomUserService {

    private final UserRepository userRepository;

    public CustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static CustomUser createCustomUserFromUser(User user){
        CustomUser customUser = new CustomUser();
        customUser.setId(user.getId());
        customUser.setUsername(user.getUsername());
        customUser.setPassword(user.getPassword());
        customUser.setRole(user.getRole());
        return customUser;
    }
}
