package pl.someday.FishingApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FishCountForSpotDTO {

    private String name;
    private Long count;

    public FishCountForSpotDTO(String name, Long count) {
        this.name = name;
        this.count = count;
    }
}
