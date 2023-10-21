package pl.someday.FishingApp.record;

import java.math.BigDecimal;

/**
 * Reprezentuje dane - ilość złowionych ryb dla danego łowiska.
 * Przechowuje informacje takie jak nazwa gatunku ryby, ilość złowionych sztuk oraz suma wag.
 */
public record FishCountForSpotRecord (String name, Long count, BigDecimal sumWeight) {

}
