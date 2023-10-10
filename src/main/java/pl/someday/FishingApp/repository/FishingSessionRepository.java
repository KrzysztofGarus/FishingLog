package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.someday.FishingApp.dto.FishCountForSpotDTO;
import pl.someday.FishingApp.dto.FishingSpotCalendarDTO;
import pl.someday.FishingApp.model.FishingSession;

import java.util.List;

@Repository
public interface FishingSessionRepository extends JpaRepository<FishingSession, Long> {

    List<FishingSession> findByUserUsernameOrderByDateDesc(String username);

    FishingSession getFishingSessionById(Long id);

    void delete(FishingSession fishingSession);

    @Query("SELECT NEW pl.someday.FishingApp.dto.FishCountForSpotDTO(fn.name, COUNT(fn.name), SUM(f.weight)) " +
            "FROM FishingSession fs " +
            "JOIN Fish f ON fs.id = f.fishingSession.id " +
            "JOIN FishName fn ON f.fishName.id = fn.id " +
            "JOIN fs.fishingSpot spot " +
            "WHERE spot.id = :spotId " +
            "GROUP BY fn.name " +
            "ORDER BY fn.name")
    List<FishCountForSpotDTO> getFishCountsAndWeightForSpot(@Param("spotId") Long spotId);


    @Query("SELECT new pl.someday.FishingApp.dto.FishingSpotCalendarDTO(fs.date, COUNT(fs)) " +
            "FROM FishingSession fs " +
            "WHERE fs.fishingSpot.id = :spotId " +
            "GROUP BY fs.date")
    List<FishingSpotCalendarDTO> getSessionCountForSpotAndDate(@Param("spotId") Long spotId);




}

