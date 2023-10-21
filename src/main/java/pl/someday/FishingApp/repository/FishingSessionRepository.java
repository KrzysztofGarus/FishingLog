package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.someday.FishingApp.record.FishCountForSpotRecord;
import pl.someday.FishingApp.record.FishingSpotCalendarRecord;
import pl.someday.FishingApp.model.FishingSession;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Interfejs repozytorium, który umożliwia dostęp do danych związanych z sesjami wędkarskimi.
 */
public interface FishingSessionRepository extends JpaRepository<FishingSession, Long> {

    /**
     * Znajduje wszystkie sesje wędkarskie użytkownika i sortuje je malejąco według daty.
     *
     * @param username Nazwa użytkownika.
     * @return Lista sesji wędkarskich użytkownika.
     */
    List<FishingSession> findByUserUsernameOrderByDateDesc(String username);

    /**
     * Pobiera sesję wędkarską o podanym identyfikatorze.
     *
     * @param id Identyfikator sesji wędkarskiej.
     * @return Sesja wędkarska o podanym identyfikatorze.
     */
    FishingSession getFishingSessionById(Long id);

    /**
     * Usuwa podaną sesję wędkarską.
     *
     * @param fishingSession Sesja wędkarska do usunięcia.
     */
    void delete(FishingSession fishingSession);

    /**
     * Pobiera statystyki dotyczące ilości i wagi złowionych ryb dla danego miejsca połowu.
     *
     * @param spotId Identyfikator miejsca połowu.
     * @return Lista obiektów DTO z danymi dotyczącymi ryb dla danego miejsca połowu.
     */
    @Query("SELECT NEW pl.someday.FishingApp.record.FishCountForSpotRecord(fn.name, COUNT(fn.name), SUM(f.weight)) " +
            "FROM FishingSession fs " +
            "JOIN Fish f ON fs.id = f.fishingSession.id " +
            "JOIN FishName fn ON f.fishName.id = fn.id " +
            "JOIN fs.fishingSpot spot " +
            "WHERE spot.id = :spotId " +
            "GROUP BY fn.name " +
            "ORDER BY fn.name")
    List<FishCountForSpotRecord> getFishCountsAndWeightForSpot(@Param("spotId") Long spotId);


    /**
     * Pobiera statystyki dotyczące ilości sesji wędkarskich dla danego miejsca połowu i daty.
     *
     * @param spotId Identyfikator miejsca połowu.
     * @return Lista obiektów DTO z danymi dotyczącymi sesji wędkarskich dla danego miejsca połowu i daty.
     */
    @Query("SELECT new pl.someday.FishingApp.record.FishingSpotCalendarRecord(fs.date, COUNT(fs)) " +
            "FROM FishingSession fs " +
            "WHERE fs.fishingSpot.id = :spotId " +
            "GROUP BY fs.date")
    List<FishingSpotCalendarRecord> getSessionCountForSpotAndDate(@Param("spotId") Long spotId);

    /**
     * Znajduje identyfikator miejsca połowu, które było najczęściej używane w sesjach wędkarskich.
     *
     * @return Opcjonalny identyfikator miejsca połowu.
     */
    @Query(value = "SELECT fishing_spot_id FROM fishing_sessions GROUP BY fishing_spot_id ORDER BY COUNT(fishing_spot_id) DESC LIMIT 1",
            nativeQuery = true)
    Optional<Long> findMostFrequentFishingSpotId();

    /**
     * Znajduje identyfikator miejsca połowu, które było najczęściej używane w sesjach wędkarskich. Jeżeli nie istnieje, rzuca wyjątek.
     *
     * @return Identyfikator miejsca połowu.
     * @throws NoSuchElementException Jeżeli nie znaleziono danych.
     */
    default Long findMostFrequentFishingSpotIdOrThrow() {
        return findMostFrequentFishingSpotId()
                .orElseThrow(() -> new NoSuchElementException("No data"));
    }

    /**
     * Pobiera statystyki dotyczące ilości sesji wędkarskich dla każdej daty.
     *
     * @return Lista obiektów DTO z danymi dotyczącymi sesji wędkarskich dla każdej daty.
     */
    @Query("SELECT new pl.someday.FishingApp.record.FishingSpotCalendarRecord(fs.date, COUNT(fs)) " +
            "FROM FishingSession fs " +
            "GROUP BY fs.date")
    List<FishingSpotCalendarRecord> getTotalSessionCountByDate();

    /**
     * Pobiera statystyki dotyczące ilości sesji wędkarskich dla każdej daty dla danego użytkownika.
     *
     * @param userId Identyfikator użytkownika.
     * @return Lista obiektów DTO z danymi dotyczącymi sesji wędkarskich dla każdej daty i użytkownika.
     */
    @Query("SELECT new pl.someday.FishingApp.record.FishingSpotCalendarRecord(fs.date, COUNT(fs)) " +
            "FROM FishingSession fs " +
            "WHERE fs.user.id = :userId " +
            "GROUP BY fs.date")
    List<FishingSpotCalendarRecord> getTotalSessionCountByDateForUser(@Param("userId") Long userId);

    /**
     * Pobiera ilość sesji wędkarskich dla danego użytkownika.
     *
     * @param userId Identyfikator użytkownika.
     * @return Ilość sesji wędkarskich użytkownika.
     */
    @Query("SELECT COUNT(fs) FROM FishingSession fs WHERE fs.user.id = :userId")
    Long getSessionCountForUser(@Param("userId") Long userId);

    /**
     * Znajduje nazwę miejsca połowu, które było najczęściej odwiedzane przez danego użytkownika.
     *
     * @param userId Identyfikator użytkownika.
     * @return Nazwa miejsca połowu.
     */
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

