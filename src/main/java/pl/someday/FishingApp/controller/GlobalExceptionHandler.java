package pl.someday.FishingApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

/**
 * Obsługuje konkretne wyjątki i definiuje, jak aplikacja powinna reagować na te wyjątki.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Obsługuje wyjątek `NoSuchElementException`.
     * W przypadku wystąpienia tego wyjątku, metoda ta ustawia kod HTTP na "NOT_FOUND" (404),
     * przekazuje komunikat błędu do modelu i zwraca nazwę widoku strony błędu.
     *
     * @param ex    Wyjątek typu `NoSuchElementException`.
     * @param model Model Spring, używany do przekazywania danych do widoku.
     * @return Nazwa widoku strony błędu.
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(NoSuchElementException ex, Model model) {
        model.addAttribute("errorMsg", ex.getMessage());
        return "/error";
    }
}
