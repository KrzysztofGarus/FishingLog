package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.someday.FishingApp.model.fishName.FishName;
import pl.someday.FishingApp.model.fishName.FishNameRepository;
import pl.someday.FishingApp.model.fishingSession.FishingSessionRepository;
import pl.someday.FishingApp.model.fishingSpot.FishingSpotRepository;
import pl.someday.FishingApp.model.user.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final FishNameRepository fishNameRepository;
    private final FishingSpotRepository fishingSpotRepository;
    private final FishingSessionRepository fishingSessionRepository;
    @Autowired
    public AdminController(UserRepository userRepository, FishNameRepository fishNameRepository, FishingSpotRepository fishingSpotRepository, FishingSessionRepository fishingSessionRepository) {
        this.userRepository = userRepository;
        this.fishNameRepository = fishNameRepository;
        this.fishingSpotRepository = fishingSpotRepository;
        this.fishingSessionRepository = fishingSessionRepository;
    }

    @GetMapping("/dashboard")
    public String showAdminDashboard(){
        return "/admin/dashboard";
    }

    @GetMapping("/fish/list")
    public String showListOfFishNames(Model model){
        model.addAttribute("fishNameList", fishNameRepository.findAll());
        return "/admin/fish-name-list";
    }

    @GetMapping("/fish/add")
    public String addFish(Model model){
        model.addAttribute("fishName", new FishName());
        return "/admin/fish-add";
    }
    @PostMapping("/fish/add")

    public String addFish(FishName fishName){
        fishNameRepository.save(fishName);
        return "redirect:/admin/fish/all";
    }

    @GetMapping("/fish/delete")
    public String deleteFish(@RequestParam Long id, Model model){
        model.addAttribute("fishName", fishNameRepository.getFishNameById(id));
        return "/admin/fish-delete";
    }
    @PostMapping("/fish/delete")
    public String deleteFish(@RequestParam Long id){
        FishName fishName = fishNameRepository.getFishNameById(id);
        fishNameRepository.delete(fishName);
        return "redirect:/admin/fish/all";
    }

}


