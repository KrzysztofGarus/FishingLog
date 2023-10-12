package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.someday.FishingApp.model.FishName;
import pl.someday.FishingApp.model.FishingSpot;
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
        return "/admin/fish/fish-name-list";
    }
    @GetMapping("/fish/update")
    public String editFish(@RequestParam Long id, Model model){
        FishName fishName = fishNameRepository.getFishNameById(id);
        model.addAttribute("fishName",fishName);
        return "/admin/fish/fish-update";
    }

    @PostMapping("/fish/update")
    public String editFish(@RequestParam Long id, @ModelAttribute("fishName") FishName updatedFishName){
        FishName fishName = fishNameRepository.getFishNameById(id);
        fishName.setName(updatedFishName.getName());
        fishNameRepository.save(fishName);
        return "redirect:/admin/fish/list";
    }

    @GetMapping("/fish/add")
    public String addFish(Model model){
        model.addAttribute("fishName", new FishName());
        return "/admin/fish/fish-add";
    }
    @PostMapping("/fish/add")

    public String addFish(FishName fishName){
        fishNameRepository.save(fishName);
        return "redirect:/admin/fish/all";
    }

    @GetMapping("/fish/delete")
    public String deleteFish(@RequestParam Long id, Model model){
        model.addAttribute("fishName", fishNameRepository.getFishNameById(id));
        return "/admin/fish/fish-delete";
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
        return "/admin/spot/spot-list";
    }

    @GetMapping("/spot/details")
    public String showDetailsOfFishingSpot(@RequestParam Long id, Model model){
        DateFormatterForDTO dto = new DateFormatterForDTO();
        model.addAttribute("fishNamesCount", fishingSessionRepository.getFishCountsAndWeightForSpot(id));
        model.addAttribute("SessionDateCount", dto.processDates(fishingSessionRepository.getSessionCountForSpotAndDate(id)));
        return "/admin/spot/spot-details";
    }
    @GetMapping("/spot/add")
    public String addSpot(Model model){
        model.addAttribute("fishingSpot", new FishingSpot());
        return "/admin/spot/spot-add";
    }
    @PostMapping ("/spot/add")
    public  String addSpot(FishingSpot fishingSpot){
        fishingSpotRepository.save(fishingSpot);
        return "redirect:/admin/spot/list";
    }

    @GetMapping("/spot/delete")
    public String deleteSpot(@RequestParam Long id, Model model){
        FishingSpot fishingSpot = fishingSpotRepository.findByIdOrThrow(id);
        model.addAttribute("fishingSpot", fishingSpot);
        return "/admin/spot/spot-delete";
    }

    @PostMapping("/spot/delete")
    public String deleteSpot(@RequestParam Long id){
        FishingSpot fishingSpot = fishingSpotRepository.findByIdOrThrow(id);
        fishingSpotRepository.delete(fishingSpot);
        return "redirect:/admin/spot/list";
    }
    @GetMapping("/spot/update")
    public String editSpot(@RequestParam Long id, Model model) {
        model.addAttribute("fishingSpot", fishingSpotRepository.findByIdOrThrow(id));
        return "/admin/spot/spot-update";
    }

    @PostMapping("/spot/update")
    public String editSpot(@RequestParam Long id, @ModelAttribute("fishingSpot") FishingSpot updatedFishingSpot){
        FishingSpot fishingSpot = fishingSpotRepository.findByIdOrThrow(id);
        fishingSpot.setName(updatedFishingSpot.getName());
        fishingSpotRepository.save(fishingSpot);
        return "redirect:/admin/spot/list";
    }
}


