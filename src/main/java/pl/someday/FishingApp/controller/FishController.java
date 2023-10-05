package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.someday.FishingApp.model.fish.Fish;
import pl.someday.FishingApp.model.fish.FishRepository;
import pl.someday.FishingApp.model.fishingSession.FishingSession;
import pl.someday.FishingApp.model.fishingSession.FishingSessionRepository;

@Controller
@RequestMapping("/user/fish")
public class FishController {
    private final FishRepository fishRepository;
    private final FishingSessionRepository fishingSessionRepository;
    @Autowired
    public FishController(FishRepository fishRepository, FishingSessionRepository fishingSessionRepository) {
        this.fishRepository = fishRepository;
        this.fishingSessionRepository = fishingSessionRepository;
    }

    @GetMapping("/add")
    public String addFish(@RequestParam Long sessionId, Model model){
        Fish fish = new Fish();
        fish.setFishingSession(fishingSessionRepository.getFishingSessionById(sessionId));
        model.addAttribute("fish", fish);
        return "/user/fish/fish-add";
    }

    @PostMapping("/add")
    public String addFishtoSession(Fish fish){
        fishRepository.save(fish);
        return "redirect:/user/session/all";
    }


}




