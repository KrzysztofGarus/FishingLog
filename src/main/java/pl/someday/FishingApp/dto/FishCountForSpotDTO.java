package pl.someday.FishingApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Klasa DTO (Data Transfer Object) reprezentująca dane dotyczące statystyk złowionych ryb
 * Wykorzystywana do przesyłania danych pomiędzy warstwą
 * prezentacji a warstwą logiki biznesowej.
 */
@Getter
@Setter
public class FishCountForSpotDTO {

    private String name;
    private Long count;
    private BigDecimal sumWeight;

    /**
     * Konstruktor tworzący obiekt FishCountForSpotDTO z podanymi danymi.
     *
     * @param name      Nazwa gatunku ryby.
     * @param count     Liczba złowionych ryb danego gatunku.
     * @param sumWeight Sumaryczna waga złowionych ryb danego gatunku.
     */
    public FishCountForSpotDTO(String name, Long count, BigDecimal sumWeight) {
        this.name = name;
        this.count = count;
        this.sumWeight = sumWeight;
    }
}
