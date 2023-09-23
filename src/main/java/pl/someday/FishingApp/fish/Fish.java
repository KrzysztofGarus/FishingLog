package pl.someday.FishingApp.fish;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "fishes")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
}
