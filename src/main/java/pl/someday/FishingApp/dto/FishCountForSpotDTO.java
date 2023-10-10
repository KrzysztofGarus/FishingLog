package pl.someday.FishingApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FishCountForSpotDTO {

    private String name;
    private Long count;
    private BigDecimal sumWeight;

    public FishCountForSpotDTO(String name, Long count, BigDecimal sumWeight) {
        this.name = name;
        this.count = count;
        this.sumWeight = sumWeight;
    }
}
