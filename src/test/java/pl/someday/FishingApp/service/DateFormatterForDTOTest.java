package pl.someday.FishingApp.service;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Klasa testowa dla DateFormatterForDTO, która odpowiada za testowanie funkcji związanych
 * z formatowaniem dat w DTO.
 */
public class DateFormatterForDTOTest {

    private final DateFormatterForDTO dateFormatter = new DateFormatterForDTO();

    /**
     * Metoda testująca funkcję convertDateToNumber, która powinna zwracać sformatowaną datę.
     * W JavaScript miesiące numerowane są od 0 :(
     */
    @Test
    void convertDateToNumber_ShouldReturnFormattedDate() {
        // Given
        LocalDate date = LocalDate.of(2023, 10, 1);

        // When
        String result = dateFormatter.convertDate(date);

        // Then
        assertThat(result).isEqualTo("2023, 9, 1");
    }
}
