package pl.someday.FishingApp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Reprezentuje dane dotyczące kalendarza sesji wędkarskich
 * w kontekście danego miejsca wędkarskiego. Zawiera informacje o dacie, liczbie sesji oraz
 * formę daty w postaci tekstu.
 */
@Getter
@Setter
@NoArgsConstructor
public class FishingSpotCalendarDTO {

    private LocalDate date;
    private Long count;
    private String stringDate;

    /**
     * Tworzy obiekt FishingSpotCalendarDTO z podanymi danymi.
     * Podczas tworzenia obiektu wykonuje konwersje daty.
     *
     * @param date  Data sesji wędkarskiej.
     * @param count Liczba sesji w danym dniu.
     */
    public FishingSpotCalendarDTO(LocalDate date, Long count) {
        this.date = date;
        this.count = count;
        this.stringDate = convertDate(date);
    }

    /**
     * Konwertuje datę na format liczbowy (RRRR, MM, DD).
     *
     * @param date Data do konwersji.
     * @return Sformatowana data jako ciąg znaków.
     */
    private String convertDate(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue() - 1;
        int day = date.getDayOfMonth();

        return year + ", " + month + ", " + day;
    }
}
