package pl.someday.FishingApp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class FishingSpotCalendarDTO {

    private LocalDate date;
    private Long count;
    private String StringDate;

    public FishingSpotCalendarDTO(LocalDate date, Long count) {
        this.date = date;
        this.count = count;
    }

    public FishingSpotCalendarDTO(LocalDate date, Long count, String StringDate) {
        this.date = date;
        this.count = count;
        this.StringDate = StringDate;
    }
}
