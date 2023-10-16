package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.someday.FishingApp.dto.FishCountForSpotDTO;
import pl.someday.FishingApp.dto.FishingSpotCalendarDTO;
import pl.someday.FishingApp.model.FishingSession;
import pl.someday.FishingApp.model.FishingSpot;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Query(value = "SELECT fishing_spot_id FROM fishing_sessions GROUP BY fishing_spot_id ORDER BY COUNT(fishing_spot_id) DESC LIMIT 1",
            nativeQuery = true)

    Optional<Long> findMostFrequentFishingSpotId();

    default Long findMostFrequentFishingSpotIdOrThrow() {
        return findMostFrequentFishingSpotId()
                .orElseThrow(() -> new NoSuchElementException("No data"));
    }

    @Query("SELECT new pl.someday.FishingApp.dto.FishingSpotCalendarDTO(fs.date, COUNT(fs)) " +
            "FROM FishingSession fs " +
            "GROUP BY fs.date")
    List<FishingSpotCalendarDTO> getTotalSessionCountByDate();

    @Query("SELECT new pl.someday.FishingApp.dto.FishingSpotCalendarDTO(fs.date, COUNT(fs)) " +
            "FROM FishingSession fs " +
            "WHERE fs.user.id = :userId " +
            "GROUP BY fs.date")
    List<FishingSpotCalendarDTO> getTotalSessionCountByDateForUser(@Param("userId") Long userId);

    @Query("SELECT COUNT(fs) FROM FishingSession fs WHERE fs.user.id = :userId")
    Long getSessionCountForUser(@Param("userId") Long userId);

    @Query(value = "SELECT fs.name " +
            "FROM fishing_spots fs " +
            "JOIN fishing_sessions fss ON fs.id = fss.fishing_spot_id " +
            "WHERE fss.user_id = :userId " +
            "GROUP BY fs.name " +
            "ORDER BY COUNT(fss.id) DESC " +
            "LIMIT 1",
            nativeQuery = true)
    String findMostFrequentFishingSpotNameForUser(@Param("userId") Long userId);

}

