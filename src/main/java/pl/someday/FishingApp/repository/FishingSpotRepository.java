package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.someday.FishingApp.model.FishingSpot;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public interface FishingSpotRepository extends JpaRepository<FishingSpot, Long> {

    List<FishingSpot> findAllByOrderByName();
    void delete(FishingSpot fishingSpot);

    default FishingSpot findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nie znaleziono Spotu o ID: " + id));
    }
}
