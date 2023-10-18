package pl.someday.FishingApp.service;

import org.springframework.stereotype.Service;
import pl.someday.FishingApp.dto.FishingSpotCalendarDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DateFormatterForDTO {

    public List<FishingSpotCalendarDTO> processDates(List<FishingSpotCalendarDTO> inputList) {
        return inputList.stream()
                .map(this::processDateInDTO)
                .collect(Collectors.toList());
    }

    FishingSpotCalendarDTO processDateInDTO(FishingSpotCalendarDTO dto) {
        LocalDate date = dto.getDate();
        String yearMonthDayString = convertDateToNumber(date);

        return new FishingSpotCalendarDTO(date, dto.getCount(), yearMonthDayString);
    }

    String convertDateToNumber(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue() - 1;
        int day = date.getDayOfMonth();

        return year + ", " + month + ", " + day;
    }
}
