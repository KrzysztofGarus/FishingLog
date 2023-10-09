package pl.someday.FishingApp.service;

import org.springframework.stereotype.Service;
import pl.someday.FishingApp.dto.FishingSpotCalendarDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DateFormatterForDTO {

    public List<FishingSpotCalendarDTO> changeDateFormat(List<FishingSpotCalendarDTO> inputlist) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy, M, d");
        for (FishingSpotCalendarDTO input : inputlist) {
            Date inputDate = input.getDate();
            String outputDate = sdf.format(inputDate);
            input.setDateString(outputDate);
        }
        return inputlist;
    }
}
