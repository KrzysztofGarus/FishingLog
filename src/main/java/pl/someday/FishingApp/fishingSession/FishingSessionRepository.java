package pl.someday.FishingApp.fishingSession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingSessionRepository extends JpaRepository<FishingSession, Long> {
}

