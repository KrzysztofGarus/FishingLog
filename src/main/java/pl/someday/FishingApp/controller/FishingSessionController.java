package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.someday.FishingApp.model.fishingSession.FishingSession;
import pl.someday.FishingApp.model.fishingSession.FishingSessionRepository;
import pl.someday.FishingApp.model.fishingSpot.FishingSpotRepository;
import pl.someday.FishingApp.model.user.User;
import pl.someday.FishingApp.model.user.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/user/session")
public class FishingSessionController {

    private final UserRepository userRepository;
    private final FishingSessionRepository fishingSessionRepository;

    private final FishingSpotRepository fishingSpotRepository;

    @Autowired
    public FishingSessionController(UserRepository userRepository, FishingSessionRepository fishingSessionRepository, FishingSpotRepository fishingSpotRepository) {
        this.userRepository = userRepository;
        this.fishingSessionRepository = fishingSessionRepository;
        this.fishingSpotRepository = fishingSpotRepository;
    }

    @GetMapping("/add")
    public String addSession(Model model){
        model.addAttribute("fishingSession", new FishingSession());
        model.addAttribute("spotList", fishingSpotRepository.findAll());
        return "/user/session/session-add";
    }

    @PostMapping("/add")
    public String addSessionForUser(@AuthenticationPrincipal User user, FishingSession fishingSession){
        fishingSession.setUser(user);
        fishingSessionRepository.save(fishingSession);
        return "redirect:/user/dashboard";
    }

    @PostMapping("/delete")
    public String deleteSession(@RequestParam Long id) {
        Optional<FishingSession> fishingSession = fishingSessionRepository.findById(id);
        fishingSessionRepository.delete(fishingSession);
        return "/user/sessions";
    }

    @GetMapping("/all")
    public String showUserSessions(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("fishingSessions", fishingSessionRepository.findByUserUsername(user.getUsername()));
        return "/user/sessions";
    }
}
