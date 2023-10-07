package pl.someday.FishingApp.model.fish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.someday.FishingApp.model.fishName.FishName;
import pl.someday.FishingApp.model.fishingSession.FishingSession;
import pl.someday.FishingApp.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

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
