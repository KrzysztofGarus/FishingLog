package pl.someday.FishingApp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class FishingSpotCalendarDTO {

    private Date date;
    private Long count;
    private String dateString;

    public FishingSpotCalendarDTO(Date date, Long count) {
        this.date = date;
        this.count = count;
    }
}
