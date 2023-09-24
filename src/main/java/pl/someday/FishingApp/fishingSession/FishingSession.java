package pl.someday.FishingApp.fishingSession;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.someday.FishingApp.fish.Fish;
import pl.someday.FishingApp.fishingSpot.FishingSpot;
import pl.someday.FishingApp.user.User;

import javax.persistence.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fishing_sessions")
public class FishingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fishing_spot_id")
    private FishingSpot fishingSpot;

    @OneToMany(mappedBy = "fishingSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fish> fishList = new ArrayList<>();
}
