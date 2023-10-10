package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.someday.FishingApp.model.Fish;
import pl.someday.FishingApp.repository.FishRepository;
import pl.someday.FishingApp.repository.FishNameRepository;
import pl.someday.FishingApp.model.FishingSession;
import pl.someday.FishingApp.repository.FishingSessionRepository;

import java.util.List;

@Controller
@RequestMapping("/user/fish")
public class FishController {

    private final FishNameRepository fishNameRepository;
    private final FishRepository fishRepository;
    private final FishingSessionRepository fishingSessionRepository;
    @Autowired
    public FishController(FishNameRepository fishNameRepository, FishRepository fishRepository, FishingSessionRepository fishingSessionRepository) {
        this.fishNameRepository = fishNameRepository;
        this.fishRepository = fishRepository;
        this.fishingSessionRepository = fishingSessionRepository;
    }

    @GetMapping("/add")
    public String addFish(@RequestParam Long sessionId, Model model){
        Fish fish = new Fish();
        fish.setFishingSession(fishingSessionRepository.getFishingSessionById(sessionId));
        model.addAttribute("fishNames", fishNameRepository.findAll());
        model.addAttribute("fish", fish);
        return "/user/fish/fish-add";
    }

    @PostMapping("/add")
    public String addFishToSession(Fish fish){
        fishRepository.save(fish);
        return "redirect:/user/session/all";
    }

    @GetMapping("/update")
    public String editFish(@RequestParam Long id, Model model) {
        Fish fish = fishRepository.getFishById(id);
        model.addAttribute("fish", fish);
        model.addAttribute("fishNames", fishNameRepository.findAll());
        return "/user/fish/fish-update";
    }

    @PostMapping("/update")
    public String editFish(@RequestParam Long id, @ModelAttribute("fish") Fish updatedFish){
        Fish fish = fishRepository.getFishById(id);
        fish.setFishName(updatedFish.getFishName());
        fish.setWeight(updatedFish.getWeight());
        fish.setLength(updatedFish.getLength());
        fishRepository.save(fish);
        return "redirect:/user/session/all";
    }

    @GetMapping("/delete")
        public String deleteFish(@RequestParam Long id, Model model){
            model.addAttribute("fish", fishRepository.getFishById(id));
            return "/user/fish/fish-delete";
        }
    @PostMapping("/delete")
    public String deleteFish(@RequestParam Long fishId, @RequestParam Long sessionId) {
        FishingSession fishingSession = fishingSessionRepository.getFishingSessionById(sessionId);
        List<Fish> fishList = fishingSession.getFishList();
        fishList.removeIf(fish -> fish.getId().equals(fishId));
        fishingSession.setFishList(fishList);
        fishingSessionRepository.save(fishingSession);
        return "redirect:/user/session/all";
    }
}




