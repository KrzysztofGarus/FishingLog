package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.someday.FishingApp.model.FishName;
import pl.someday.FishingApp.repository.FishNameRepository;
import pl.someday.FishingApp.repository.FishingSessionRepository;
import pl.someday.FishingApp.repository.FishingSpotRepository;
import pl.someday.FishingApp.repository.UserRepository;
import pl.someday.FishingApp.service.DateFormatterForDTO;

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

    @GetMapping("/spot/list")
    public String showListOfFishingSpots(Model model){
        model.addAttribute("fishingSpotList", fishingSpotRepository.findAllByOrderByName());
        return "/admin/spot-list";
    }

    @GetMapping("/spot/details")
    public String showDetailsOfFishingSpot(@RequestParam Long id, Model model){
        DateFormatterForDTO dto = new DateFormatterForDTO();
        model.addAttribute("fishNamesCount", fishingSessionRepository.getFishCountsForSpot(id));
        model.addAttribute("SessionDateCount", dto.processDates(fishingSessionRepository.getSessionCountForSpotAndDate(id)));
        return "/admin/spot-details";
    }

}


