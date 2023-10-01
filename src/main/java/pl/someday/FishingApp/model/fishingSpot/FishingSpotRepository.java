package pl.someday.FishingApp.model.fishingSpot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingSpotRepository extends JpaRepository<FishingSpot, Long> {

}
