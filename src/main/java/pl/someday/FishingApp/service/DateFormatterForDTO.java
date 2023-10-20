package pl.someday.FishingApp.service;

import org.springframework.stereotype.Service;
import pl.someday.FishingApp.dto.FishingSpotCalendarDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serwis obsługujący formatowanie dat w obiektach DTO związanych z kalendarzem miejsc połowów.
 */
@Service
public class DateFormatterForDTO {

    /**
     * Formatuje daty w liście obiektów DTO związanych z kalendarzem miejsc połowów.
     *
     * @param inputList Lista obiektów DTO przed formatowaniem.
     * @return Sformatowana lista obiektów DTO.
     */
    public List<FishingSpotCalendarDTO> processDates(List<FishingSpotCalendarDTO> inputList) {
        return inputList.stream()
                .map(this::processDateInDTO)
                .collect(Collectors.toList());
    }

    /**
     * Formatuje datę w obiekcie DTO związanych z kalendarzem miejsc połowów.
     *
     * @param dto Obiekt DTO przed formatowaniem daty.
     * @return Sformatowany obiekt DTO.
     */
    FishingSpotCalendarDTO processDateInDTO(FishingSpotCalendarDTO dto) {
        LocalDate date = dto.getDate();
        String yearMonthDayString = convertDateToNumber(date);

        return new FishingSpotCalendarDTO(date, dto.getCount(), yearMonthDayString);
    }

    /**
     * Konwertuje datę na format liczbowy (RRRR, MM, DD).
     *
     * @param date Data do konwersji.
     * @return Sformatowana data jako ciąg znaków.
     */
    String convertDateToNumber(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue() - 1;
        int day = date.getDayOfMonth();

        return year + ", " + month + ", " + day;
    }
}
