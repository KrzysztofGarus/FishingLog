package pl.someday.FishingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.someday.FishingApp.model.FishName;
import pl.someday.FishingApp.model.FishingSpot;
import pl.someday.FishingApp.repository.*;
import pl.someday.FishingApp.service.DateFormatterForDTO;

/**
 * Kontroler obsługujący interakcje związane z administracją systemu.
 * Klasa ta zarządza różnymi operacjami administracyjnymi, takimi jak wyświetlanie
 * statystyk, zarządzanie danymi dotyczącymi ryb, miejsc wędkowania oraz użytkowników.
 */
@Controller
@RequestMapping("/admin")
public class AdminViewController {

    private final UserRepository userRepository;
    private final FishRepository fishRepository;
    private final FishNameRepository fishNameRepository;
    private final FishingSpotRepository fishingSpotRepository;
    private final FishingSessionRepository fishingSessionRepository;

    /**
     * Konstruktor klasy `AdminViewController`.
     *
     * @param userRepository          Repozytorium użytkowników.
     * @param fishRepository          Repozytorium ryb.
     * @param fishNameRepository      Repozytorium nazw ryb.
     * @param fishingSpotRepository   Repozytorium miejsc wędkowania.
     * @param fishingSessionRepository Repozytorium sesji wędkarskich.
     */
    @Autowired
    public AdminViewController(UserRepository userRepository, FishRepository fishRepository, FishNameRepository fishNameRepository, FishingSpotRepository fishingSpotRepository, FishingSessionRepository fishingSessionRepository) {
        this.userRepository = userRepository;
        this.fishRepository = fishRepository;
        this.fishNameRepository = fishNameRepository;
        this.fishingSpotRepository = fishingSpotRepository;
        this.fishingSessionRepository = fishingSessionRepository;
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia panelu administracyjnego.
     * Prezentuje różne statystyki i informacje dotyczące systemu dla administratora.
     *
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku panelu administracyjnego.
     */
    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model){
        DateFormatterForDTO dto = new DateFormatterForDTO();
        model.addAttribute("sessionDateCount", dto.processDates(fishingSessionRepository.getTotalSessionCountByDate()));
        model.addAttribute("activeUsersCount", userRepository.countActiveUsers());
        model.addAttribute("fishingSessionsCount", fishingSessionRepository.count());
        model.addAttribute("fishCount", fishRepository.count());
        model.addAttribute("popularSpot",fishingSpotRepository.findByIdOrThrow(fishingSessionRepository.findMostFrequentFishingSpotIdOrThrow()).getName());
        return "/admin/dashboard";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia listy nazw ryb.
     *
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku listy nazw ryb.
     */
    @GetMapping("/fish/list")
    public String showListOfFishNames(Model model){
        model.addAttribute("fishNameList", fishNameRepository.findAll());
        return "admin/fish-name-list";
    }

    /**
     * Metoda obsługująca żądanie edycji nazwy ryby.
     *
     * @param id    Identyfikator nazwy ryby do edycji.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza edycji nazwy ryby.
     */
    @GetMapping("/fish/update")
    public String editFish(@RequestParam Long id, Model model){
        FishName fishName = fishNameRepository.findByIdOrThrow(id);
        model.addAttribute("fishName",fishName);
        return "admin/fish-update";
    }

    /**
     * Metoda obsługująca proces zapisu zaktualizowanej nazwy ryby.
     *
     * @param id            Identyfikator nazwy ryby do zaktualizowania.
     * @param updatedFishName Zaktualizowana nazwa ryby.
     * @return Przekierowanie na widok listy nazw ryb.
     */
    @PostMapping("/fish/update")
    public String editFish(@RequestParam Long id, @ModelAttribute("fishName") FishName updatedFishName){
        FishName fishName = fishNameRepository.findByIdOrThrow(id);
        fishName.setName(updatedFishName.getName());
        fishNameRepository.save(fishName);
        return "redirect:/admin/fish/list";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia formularza dodawania nowej nazwy ryby.
     *
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza dodawania nowej nazwy ryby.
     */
    @GetMapping("/fish/add")
    public String addFish(Model model){
        model.addAttribute("fishName", new FishName());
        return "admin/fish-add";
    }

    /**
     * Metoda obsługująca proces dodawania nowej nazwy ryby.
     *
     * @param fishName Nowa nazwa ryby do dodania.
     * @return Przekierowanie na widok listy nazw ryb.
     */
    @PostMapping("/fish/add")

    public String addFish(FishName fishName){
        fishNameRepository.save(fishName);
        return "redirect:/admin/fish/all";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia formularza potwierdzenia usunięcia nazwy ryby.
     *
     * @param id    Identyfikator nazwy ryby do usunięcia.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza potwierdzenia usunięcia nazwy ryby.
     */
    @GetMapping("/fish/delete")
    public String deleteFish(@RequestParam Long id, Model model){
        model.addAttribute("fishName", fishNameRepository.findByIdOrThrow(id));
        return "admin/fish-delete";
    }

    /**
     * Metoda obsługująca proces usunięcia nazwy ryby.
     *
     * @param id Identyfikator nazwy ryby do usunięcia.
     * @return Przekierowanie na widok listy nazw ryb.
     */
    @PostMapping("/fish/delete")
    public String deleteFish(@RequestParam Long id){
        FishName fishName = fishNameRepository.findByIdOrThrow(id);
        fishNameRepository.delete(fishName);
        return "redirect:/admin/fish/all";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia listy miejsc wędkowania.
     *
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku listy miejsc wędkowania.
     */
    @GetMapping("/spot/list")
    public String showListOfFishingSpots(Model model){
        model.addAttribute("fishingSpotList", fishingSpotRepository.findAllByOrderByName());
        return "admin/spot-list";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia szczegółów danego miejsca wędkowania.
     *
     * @param id    Identyfikator miejsca wędkowania.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku zawierającego szczegóły miejsca wędkowania.
     */
    @GetMapping("/spot/details")
    public String showDetailsOfFishingSpot(@RequestParam Long id, Model model){
        DateFormatterForDTO dto = new DateFormatterForDTO();
        model.addAttribute("fishNamesCount", fishingSessionRepository.getFishCountsAndWeightForSpot(id));
        model.addAttribute("SessionDateCount", dto.processDates(fishingSessionRepository.getSessionCountForSpotAndDate(id)));
        model.addAttribute("fishingSpotName", fishingSpotRepository.findByIdOrThrow(id).getName());
        return "admin/spot-details";
    }

    /**
     * Metoda obsługująca żądanie dodania nowego miejsca wędkowania.
     *
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza dodawania nowego miejsca wędkowania.
     */
    @GetMapping("/spot/add")
    public String addSpot(Model model){
        model.addAttribute("fishingSpot", new FishingSpot());
        return "admin/spot-add";
    }

    /**
     * Metoda obsługująca proces dodawania nowego miejsca wędkowania.
     *
     * @param fishingSpot Nowe miejsce wędkowania do dodania.
     * @return Przekierowanie na widok listy miejsc wędkowania.
     */
    @PostMapping ("/spot/add")
    public  String addSpot(FishingSpot fishingSpot){
        fishingSpotRepository.save(fishingSpot);
        return "redirect:/admin/spot/list";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia formularza potwierdzenia usunięcia miejsca wędkowania.
     *
     * @param id    Identyfikator miejsca wędkowania do usunięcia.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza potwierdzenia usunięcia miejsca wędkowania.
     */
    @GetMapping("/spot/delete")
    public String deleteSpot(@RequestParam Long id, Model model){
        FishingSpot fishingSpot = fishingSpotRepository.findByIdOrThrow(id);
        model.addAttribute("fishingSpot", fishingSpot);
        return "admin/spot-delete";
    }

    /**
     * Metoda obsługująca proces usunięcia miejsca wędkowania.
     *
     * @param id Identyfikator miejsca wędkowania do usunięcia.
     * @return Przekierowanie na widok listy miejsc wędkowania.
     */
    @PostMapping("/spot/delete")
    public String deleteSpot(@RequestParam Long id){
        FishingSpot fishingSpot = fishingSpotRepository.findByIdOrThrow(id);
        fishingSpotRepository.delete(fishingSpot);
        return "redirect:/admin/spot/list";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia formularza edycji miejsca wędkowania.
     *
     * @param id    Identyfikator miejsca wędkowania do edycji.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza edycji miejsca wędkowania.
     */
    @GetMapping("/spot/update")
    public String editSpot(@RequestParam Long id, Model model) {
        model.addAttribute("fishingSpot", fishingSpotRepository.findByIdOrThrow(id));
        return "admin/spot-update";
    }

    /**
     * Metoda obsługująca proces zapisu zaktualizowanego miejsca wędkowania.
     *
     * @param id               Identyfikator miejsca wędkowania do zaktualizowania.
     * @param updatedFishingSpot Zaktualizowane dane miejsca wędkowania.
     * @return Przekierowanie na widok listy miejsc wędkowania.
     */
    @PostMapping("/spot/update")
    public String editSpot(@RequestParam Long id, @ModelAttribute("fishingSpot") FishingSpot updatedFishingSpot){
        FishingSpot fishingSpot = fishingSpotRepository.findByIdOrThrow(id);
        fishingSpot.setName(updatedFishingSpot.getName());
        fishingSpotRepository.save(fishingSpot);
        return "redirect:/admin/spot/list";
    }
}


