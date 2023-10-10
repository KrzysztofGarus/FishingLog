package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.someday.FishingApp.model.FishingSpot;

import java.util.List;

@Repository
public interface FishingSpotRepository extends JpaRepository<FishingSpot, Long> {

    List<FishingSpot> findAllByOrderByName();

}
