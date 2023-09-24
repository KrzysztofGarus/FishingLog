package pl.someday.FishingApp.fishingSpot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.someday.FishingApp.fishingSession.FishingSession;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fishing_spots")
public class FishingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "fishingSpot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FishingSession> fishingSessions;
}
