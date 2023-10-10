package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.someday.FishingApp.repository.FishingSessionRepository;
import pl.someday.FishingApp.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final FishingSessionRepository fishingSessionRepository;

    @Autowired
    public UserController(UserRepository userRepository, FishingSessionRepository fishingSessionRepository) {
        this.userRepository = userRepository;
        this.fishingSessionRepository = fishingSessionRepository;
    }

    @GetMapping("/dashboard")
    public String showUserDashboard(){
        return "/user/dashboard";
    }



    @GetMapping("/maps")
    public String showMapForSelectedID(){
        // TODO - dodać obsługę parametru żądania
        return "/user/maps";
    }

}


