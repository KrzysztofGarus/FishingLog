package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.someday.FishingApp.model.Fish;
import pl.someday.FishingApp.model.FishingSession;
import pl.someday.FishingApp.model.User;
import pl.someday.FishingApp.repository.*;
import pl.someday.FishingApp.service.DateFormatterForDTO;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserViewController {
    private final FishingSessionRepository fishingSessionRepository;
    private final FishingSpotRepository fishingSpotRepository;
    private final FishNameRepository fishNameRepository;
    private final FishRepository fishRepository;

    @Autowired
    public UserViewController(FishingSessionRepository fishingSessionRepository, FishingSpotRepository fishingSpotRepository, FishNameRepository fishNameRepository, FishRepository fishRepository) {
        this.fishingSessionRepository = fishingSessionRepository;
        this.fishingSpotRepository = fishingSpotRepository;
        this.fishNameRepository = fishNameRepository;
        this.fishRepository = fishRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ISO_DATE));
            }
        });
    }

    @GetMapping("/dashboard")
    public String showUserDashboard(@AuthenticationPrincipal User user, Model model){
        DateFormatterForDTO dto = new DateFormatterForDTO();
        Long userId = user.getId();
        model.addAttribute("sessionDateCount", dto.processDates(fishingSessionRepository.
             getTotalSessionCountByDateForUser(userId)));
        model.addAttribute("sessionsCount", fishingSessionRepository.getSessionCountForUser(userId));
        model.addAttribute("popularSpot", fishingSessionRepository.findMostFrequentFishingSpotNameForUser(userId));
        model.addAttribute("fishMaxLength", fishRepository.findLongestFishForUser(userId));
        model.addAttribute("fishMaxWeight", fishRepository.findHeaviestFishForUser(userId));
        return "/user/dashboard";
    }

    @GetMapping("/maps")
    public String showMapForSelectedID(){
        return "/user/maps";
    }

    @GetMapping("/fish/add")
    public String addFish(@RequestParam Long sessionId, Model model){
        Fish fish = new Fish();
        fish.setFishingSession(fishingSessionRepository.getFishingSessionById(sessionId));
        model.addAttribute("fishNames", fishNameRepository.findAll());
        model.addAttribute("fish", fish);
        return "user/fish-add";
    }

    @PostMapping("/fish/add")
    public String addFishToSession(Fish fish){
        fishRepository.save(fish);
        return "redirect:/user/session/list";
    }

    @GetMapping("/fish/update")
    public String editFish(@RequestParam Long id, Model model) {
        Fish fish = fishRepository.getFishById(id);
        model.addAttribute("fish", fish);
        model.addAttribute("fishNames", fishNameRepository.findAll());
        return "user/fish-update";
    }

    @PostMapping("/fish/update")
    public String editFish(@RequestParam Long id, @ModelAttribute("fish") Fish updatedFish){
        Fish fish = fishRepository.getFishById(id);
        fish.setFishName(updatedFish.getFishName());
        fish.setWeight(updatedFish.getWeight());
        fish.setLength(updatedFish.getLength());
        fishRepository.save(fish);
        return "redirect:/user/session/list";
    }

    @GetMapping("/fish/delete")
    public String deleteFish(@RequestParam Long id, Model model){
        model.addAttribute("fish", fishRepository.getFishById(id));
        return "user/fish-delete";
    }
    @PostMapping("/fish/delete")
    public String deleteFish(@RequestParam Long fishId, @RequestParam Long sessionId) {
        FishingSession fishingSession = fishingSessionRepository.getFishingSessionById(sessionId);
        List<Fish> fishList = fishingSession.getFishList();
        fishList.removeIf(fish -> fish.getId().equals(fishId));
        fishingSession.setFishList(fishList);
        fishingSessionRepository.save(fishingSession);
        return "redirect:/user/session/list";
    }

    @GetMapping("/session/add")
    public String addSession(Model model){
        model.addAttribute("fishingSession", new FishingSession());
        model.addAttribute("spotList", fishingSpotRepository.findAll());
        return "/user/session-add";
    }

    @PostMapping("/session/add")
    public String addSessionForUser(@AuthenticationPrincipal User user, FishingSession fishingSession){
        fishingSession.setUser(user);
        fishingSessionRepository.save(fishingSession);
        return "redirect:/user/session/list";
    }

    @GetMapping("/session/delete")
    public String deleteSessionID(@RequestParam Long id, Model model){
        model.addAttribute("fishingSession", fishingSessionRepository.getFishingSessionById(id));
        return "/user/session-delete";
    }
    @PostMapping("/session/delete")
    public String deleteSession(@RequestParam Long id) {
        FishingSession fishingSession = fishingSessionRepository.getFishingSessionById(id);
        fishingSessionRepository.delete(fishingSession);
        return "redirect:/user/session/list";
    }

    @GetMapping("/session/list")
    public String showUserSessions(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("fishingSessions", fishingSessionRepository.findByUserUsernameOrderByDateDesc(user.getUsername()));
        return "user/session-list";
    }

    @GetMapping("/session/update")
    public String editSession(@RequestParam Long id, Model model){
        FishingSession fishingSession = fishingSessionRepository.getFishingSessionById(id);
        model.addAttribute("fishingSession", fishingSession);
        model.addAttribute("spotList", fishingSpotRepository.findAll());
        return "/user/session-update";
    }

    @PostMapping("/session/update")
    public String editSession(@RequestParam Long id, @ModelAttribute("fishingSession") FishingSession updatedSession) {
        FishingSession fishingSession = fishingSessionRepository.getFishingSessionById(id);
        fishingSession.setDate(updatedSession.getDate());
        fishingSession.setFishingSpot(updatedSession.getFishingSpot());
        fishingSessionRepository.save(fishingSession);
        return "redirect:/user/session/list";
    }

}


