package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.someday.FishingApp.model.fishingSession.FishingSession;
import pl.someday.FishingApp.model.fishingSession.FishingSessionRepository;
import pl.someday.FishingApp.model.user.User;
import pl.someday.FishingApp.model.user.UserRepository;

import java.util.List;

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

    @GetMapping("/sessions")
    public String showUserSessions(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("fishingSessions", fishingSessionRepository.findByUserUsername(user.getUsername()));
        return "/user/sessions";
    }

}


