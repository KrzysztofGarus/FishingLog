package pl.someday.FishingApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Klasa encji reprezentująca informacje o złowionej rybie.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fishes")
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fishes_names_id")
    private FishName fishName;

    @Column(precision = 4, scale = 1)
    private BigDecimal length;

    @Column(precision = 5, scale = 2)
    private BigDecimal weight;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private FishingSession fishingSession;
}
