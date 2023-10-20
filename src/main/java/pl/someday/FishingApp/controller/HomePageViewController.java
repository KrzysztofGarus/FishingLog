package pl.someday.FishingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Kontroler obsługujący widok strony głównej.
 * Klasa ta jest odpowiedzialna za obsługę żądania dotyczącego wyświetlenia strony głównej,
 * które jest mapowane na adres URL "/".
 */
@Controller
public class HomePageViewController {

    /**
     * Metoda obsługująca żądanie wyświetlenia strony głównej.
     *
     * @return Nazwa widoku strony głównej.
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
