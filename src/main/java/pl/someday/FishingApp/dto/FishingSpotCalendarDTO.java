package pl.someday.FishingApp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
/**
 * Klasa DTO (Data Transfer Object) reprezentująca dane dotyczące kalendarza sesji wędkarskich
 * w kontekście danego miejsca wędkarskiego. Zawiera informacje o dacie, liczbie sesji oraz
 * opcjonalnie formę daty w postaci tekstu.
 */
@Getter
@Setter
@NoArgsConstructor
public class FishingSpotCalendarDTO {

    private LocalDate date;
    private Long count;
    private String stringDate;

    /**
     * Konstruktor tworzący obiekt FishingSpotCalendarDTO z podanymi danymi (bez tekstu daty).
     *
     * @param date  Data sesji wędkarskiej.
     * @param count Liczba sesji w danym dniu.
     */
    public FishingSpotCalendarDTO(LocalDate date, Long count) {
        this.date = date;
        this.count = count;
    }

    /**
     * Konstruktor tworzący obiekt FishingSpotCalendarDTO z podanymi danymi (z tekstem daty).
     *
     * @param date      Data sesji wędkarskiej.
     * @param count     Liczba sesji w danym dniu.
     * @param stringDate Tekstowa reprezentacja daty.
     */
    public FishingSpotCalendarDTO(LocalDate date, Long count, String stringDate) {
        this.date = date;
        this.count = count;
        this.stringDate = stringDate;
    }
}
