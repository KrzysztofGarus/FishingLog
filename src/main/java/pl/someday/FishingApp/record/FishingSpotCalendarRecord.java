package pl.someday.FishingApp.record;

import java.time.LocalDate;

/**
 * Reprezentuje dane dotyczące kalendarza sesji wędkarskich
 * w kontekście danego miejsca wędkarskiego. Zawiera informacje o dacie, liczbie sesji oraz
 * formę daty w postaci tekstu.
 */

public record FishingSpotCalendarRecord(LocalDate date, Long count, String stringDate) {

    /**
     * Tworzy record z podanymi danymi.
     * Podczas tworzenia obiektu wykonuje konwersje daty.
     *
     * @param date  Data sesji wędkarskiej.
     * @param count Liczba sesji w danym dniu.
     */
    public FishingSpotCalendarRecord(LocalDate date, Long count) {
        this(date, count, convertDate(date));
    }

    /**
     * Konwertuje datę na format liczbowy (RRRR, MM, DD).
     *
     * @param date Data do konwersji.
     * @return Sformatowana data jako ciąg znaków.
     */
    private static String convertDate(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue() - 1;
        int day = date.getDayOfMonth();

        return year + ", " + month + ", " + day;
    }
}
