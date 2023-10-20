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

/**
 * Kontroler obsługujący interakcje użytkownika w ramach funkcji przypisanych do roli "USER".
 * Udostępnia operacje takie jak przeglądanie statystyk dotyczących sesji wędkarskich,
 * dodawanie i edycja informacji o złowionych rybach oraz zarządzanie sesjami wędkarskimi.
 */
@Controller
@RequestMapping("/user")
public class UserViewController {
    private final FishingSessionRepository fishingSessionRepository;
    private final FishingSpotRepository fishingSpotRepository;
    private final FishNameRepository fishNameRepository;
    private final FishRepository fishRepository;

    /**
     * Konstruktor kontrolera, wstrzykujący zależności do repozytoriów.
     *
     * @param fishingSessionRepository Repozytorium sesji wędkarskich.
     * @param fishingSpotRepository    Repozytorium miejsc wędkowania.
     * @param fishNameRepository       Repozytorium nazw ryb.
     * @param fishRepository           Repozytorium danych o rybach.
     */
    @Autowired
    public UserViewController(FishingSessionRepository fishingSessionRepository, FishingSpotRepository fishingSpotRepository, FishNameRepository fishNameRepository, FishRepository fishRepository) {
        this.fishingSessionRepository = fishingSessionRepository;
        this.fishingSpotRepository = fishingSpotRepository;
        this.fishNameRepository = fishNameRepository;
        this.fishRepository = fishRepository;
    }

    /**
     * Metoda inicjalizująca binder dla kontrolera.
     * Binder jest używany do konfiguracji przekształceń danych wejściowych,
     * takich jak konwersja tekstu na datę.
     *
     * @param binder Obiekt WebDataBinder, używany do konfiguracji przekształceń danych wejściowych.
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ISO_DATE));
            }
        });
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia panelu użytkownika (dashboard).
     * Wyświetla statystyki dotyczące sesji wędkarskich, takie jak liczba sesji, popularne miejsce,
     * maksymalna długość i waga złowionych ryb.
     *
     * @param user  Zalogowany użytkownik.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku panelu użytkownika.
     */
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

    /**
     * Metoda obsługująca żądanie wyświetlenia mapy.
     *
     * @return Nazwa widoku mapy.
     */
    @GetMapping("/maps")
    public String showMapForSelectedID(){
        return "/user/maps";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia formularza dodawania nowego zarejestrowanego rybu do sesji wędkarskiej.
     *
     * @param sessionId Identyfikator sesji wędkarskiej.
     * @param model     Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza dodawania ryby.
     */
    @GetMapping("/fish/add")
    public String addFish(@RequestParam Long sessionId, Model model){
        Fish fish = new Fish();
        fish.setFishingSession(fishingSessionRepository.getFishingSessionById(sessionId));
        model.addAttribute("fishNames", fishNameRepository.findAll());
        model.addAttribute("fish", fish);
        return "user/fish-add";
    }

    /**
     * Metoda obsługująca proces dodawania nowego zarejestrowanego rybu do sesji wędkarskiej.
     *
     * @param fish Nowy ryba do dodania.
     * @return Przekierowanie na widok listy sesji wędkarskich.
     */
    @PostMapping("/fish/add")
    public String addFishToSession(Fish fish){
        fishRepository.save(fish);
        return "redirect:/user/session/list";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia formularza edycji informacji o zarejestrowanym rybie.
     *
     * @param id    Identyfikator ryby do edycji.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza edycji ryby.
     */
    @GetMapping("/fish/update")
    public String editFish(@RequestParam Long id, Model model) {
        Fish fish = fishRepository.getFishById(id);
        model.addAttribute("fish", fish);
        model.addAttribute("fishNames", fishNameRepository.findAll());
        return "user/fish-update";
    }

    /**
     * Metoda obsługująca proces edycji informacji o zarejestrowanym rybie.
     *
     * @param id          Identyfikator ryby do edycji.
     * @param updatedFish Zaktualizowana informacja o rybie.
     * @return Przekierowanie na widok listy sesji wędkarskich.
     */
    @PostMapping("/fish/update")
    public String editFish(@RequestParam Long id, @ModelAttribute("fish") Fish updatedFish){
        Fish fish = fishRepository.getFishById(id);
        fish.setFishName(updatedFish.getFishName());
        fish.setWeight(updatedFish.getWeight());
        fish.setLength(updatedFish.getLength());
        fishRepository.save(fish);
        return "redirect:/user/session/list";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia formularza potwierdzenia usunięcia informacji o zarejestrowanym rybie.
     *
     * @param id    Identyfikator ryby do usunięcia.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza potwierdzenia usunięcia ryby.
     */
    @GetMapping("/fish/delete")
    public String deleteFish(@RequestParam Long id, Model model){
        model.addAttribute("fish", fishRepository.getFishById(id));
        return "user/fish-delete";
    }

    /**
     * Metoda obsługująca proces usunięcia informacji o zarejestrowanym rybie z sesji wędkarskiej.
     *
     * @param fishId    Identyfikator ryby do usunięcia.
     * @param sessionId Identyfikator sesji wędkarskiej, z której usuwana jest ryba.
     * @return Przekierowanie na widok listy sesji wędkarskich.
     */
    @PostMapping("/fish/delete")
    public String deleteFish(@RequestParam Long fishId, @RequestParam Long sessionId) {
        FishingSession fishingSession = fishingSessionRepository.getFishingSessionById(sessionId);
        List<Fish> fishList = fishingSession.getFishList();
        fishList.removeIf(fish -> fish.getId().equals(fishId));
        fishingSession.setFishList(fishList);
        fishingSessionRepository.save(fishingSession);
        return "redirect:/user/session/list";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia formularza dodawania nowej sesji wędkarskiej.
     *
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza dodawania sesji wędkarskiej.
     */
    @GetMapping("/session/add")
    public String addSession(Model model){
        model.addAttribute("fishingSession", new FishingSession());
        model.addAttribute("spotList", fishingSpotRepository.findAll());
        return "/user/session-add";
    }

    /**
     * Metoda obsługująca proces dodawania nowej sesji wędkarskiej dla zalogowanego użytkownika.
     *
     * @param user           Zalogowany użytkownik.
     * @param fishingSession Nowa sesja wędkarska do dodania.
     * @return Przekierowanie na widok listy sesji wędkarskich.
     */
    @PostMapping("/session/add")
    public String addSessionForUser(@AuthenticationPrincipal User user, FishingSession fishingSession){
        fishingSession.setUser(user);
        fishingSessionRepository.save(fishingSession);
        return "redirect:/user/session/list";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia formularza potwierdzenia usunięcia sesji wędkarskiej.
     *
     * @param id    Identyfikator sesji wędkarskiej do usunięcia.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza potwierdzenia usunięcia sesji wędkarskiej.
     */
    @GetMapping("/session/delete")
    public String deleteSessionID(@RequestParam Long id, Model model){
        model.addAttribute("fishingSession", fishingSessionRepository.getFishingSessionById(id));
        return "/user/session-delete";
    }
    /**
     * Metoda obsługująca proces usunięcia sesji wędkarskiej dla zalogowanego użytkownika.
     *
     * @param id Identyfikator sesji wędkarskiej do usunięcia.
     * @return Przekierowanie na widok listy sesji wędkarskich.
     */
    @PostMapping("/session/delete")
    public String deleteSession(@RequestParam Long id) {
        FishingSession fishingSession = fishingSessionRepository.getFishingSessionById(id);
        fishingSessionRepository.delete(fishingSession);
        return "redirect:/user/session/list";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia listy sesji wędkarskich użytkownika.
     *
     * @param user  Zalogowany użytkownik.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku listy sesji wędkarskich użytkownika.
     */
    @GetMapping("/session/list")
    public String showUserSessions(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("fishingSessions", fishingSessionRepository.findByUserUsernameOrderByDateDesc(user.getUsername()));
        return "user/session-list";
    }

    /**
     * Metoda obsługująca żądanie wyświetlenia formularza edycji istniejącej sesji wędkarskiej.
     *
     * @param id    Identyfikator sesji wędkarskiej do edycji.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku formularza edycji sesji wędkarskiej.
     */
    @GetMapping("/session/update")
    public String editSession(@RequestParam Long id, Model model){
        FishingSession fishingSession = fishingSessionRepository.getFishingSessionById(id);
        model.addAttribute("fishingSession", fishingSession);
        model.addAttribute("spotList", fishingSpotRepository.findAll());
        return "/user/session-update";
    }

    /**
     * Metoda obsługująca proces edycji sesji wędkarskiej dla zalogowanego użytkownika.
     *
     * @param id            Identyfikator sesji wędkarskiej do edycji.
     * @param updatedSession Zaktualizowane informacje o sesji wędkarskiej.
     * @return Przekierowanie na widok listy sesji wędkarskich.
     */
    @PostMapping("/session/update")
    public String editSession(@RequestParam Long id, @ModelAttribute("fishingSession") FishingSession updatedSession) {
        FishingSession fishingSession = fishingSessionRepository.getFishingSessionById(id);
        fishingSession.setDate(updatedSession.getDate());
        fishingSession.setFishingSpot(updatedSession.getFishingSpot());
        fishingSessionRepository.save(fishingSession);
        return "redirect:/user/session/list";
    }

}


