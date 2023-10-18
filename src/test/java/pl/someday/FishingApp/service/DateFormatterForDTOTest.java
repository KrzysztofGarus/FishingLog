package pl.someday.FishingApp.service;

import org.junit.jupiter.api.Test;
import pl.someday.FishingApp.dto.FishingSpotCalendarDTO;
import pl.someday.FishingApp.service.DateFormatterForDTO;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DateFormatterForDTOTest {

    private final DateFormatterForDTO dateFormatter = new DateFormatterForDTO();

    @Test
    void convertDateToNumber_ShouldReturnFormattedDate() {
        // Given
        LocalDate date = LocalDate.of(2023, 10, 1);

        // When
        String result = dateFormatter.convertDateToNumber(date);

        // Then
        assertThat(result).isEqualTo("2023, 9, 1");
    }
}
