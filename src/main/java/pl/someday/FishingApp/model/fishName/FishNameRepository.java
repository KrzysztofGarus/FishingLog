package pl.someday.FishingApp.model.fishName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.someday.FishingApp.model.fishingSession.FishingSession;
import pl.someday.FishingApp.model.fishingSpot.FishingSpot;

@Repository
public interface FishNameRepository extends JpaRepository<FishName, Long> {

    FishName getFishNameById(Long id);

}

