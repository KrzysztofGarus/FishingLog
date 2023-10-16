package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.someday.FishingApp.model.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {

    Fish getFishById(Long id);
    void delete(Fish fish);

    @Query(value = "SELECT * FROM fishes f " +
            "JOIN fishing_sessions fs ON f.session_id = fs.id " +
            "JOIN users u ON fs.user_id = u.id " +
            "WHERE u.id = :userId " +
            "ORDER BY f.length DESC " +
            "LIMIT 1", nativeQuery = true)
    Fish findLongestFishForUser(@Param("userId") Long userId);


    @Query(value = "SELECT * FROM fishes f " +
            "JOIN fishing_sessions fs ON f.session_id = fs.id " +
            "JOIN users u ON fs.user_id = u.id " +
            "WHERE u.id = :userId " +
            "ORDER BY f.weight DESC " +
            "LIMIT 1", nativeQuery = true)
    Fish findHeaviestFishForUser(@Param("userId") Long userId);

}
