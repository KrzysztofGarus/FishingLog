package pl.someday.FishingApp.model.fishingSession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FishingSessionRepository extends JpaRepository<FishingSession, Long> {

    List<FishingSession> findByUserUsername(String username);

    FishingSession getFishingSessionById(Long id);

    void delete(FishingSession fishingSession);
}

