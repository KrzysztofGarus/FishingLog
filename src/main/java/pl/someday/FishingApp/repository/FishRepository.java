package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.someday.FishingApp.model.Fish;

/**
 * Interfejs repozytorium do obsługi operacji bazodanowych na encji Fish.
 */
public interface FishRepository extends JpaRepository<Fish, Long> {

    /**
     * Pobiera obiekt Fish na podstawie jego identyfikatora.
     *
     * @param id Identyfikator Fish do pobrania.
     * @return Znaleziony obiekt Fish lub null, jeśli nie istnieje obiekt o podanym identyfikatorze.
     */
    Fish getFishById(Long id);
    /**
     * Usuwa obiekt Fish z bazy danych.
     *
     * @param fish Obiekt Fish do usunięcia.
     */
    void delete(Fish fish);

    /**
     * Znajduje najdłuższą rybę dla danego użytkownika.
     *
     * @param userId Identyfikator użytkownika, dla którego ma być znaleziona najdłuższa ryba.
     * @return Obiekt Fish reprezentujący najdłuższą rybę dla danego użytkownika.
     */
    @Query(value = "SELECT * FROM fishes f " +
            "JOIN fishing_sessions fs ON f.session_id = fs.id " +
            "JOIN users u ON fs.user_id = u.id " +
            "WHERE u.id = :userId " +
            "ORDER BY f.length DESC " +
            "LIMIT 1", nativeQuery = true)
    Fish findLongestFishForUser(@Param("userId") Long userId);

    /**
     * Znajduje najcięższą rybę dla danego użytkownika.
     *
     * @param userId Identyfikator użytkownika, dla którego ma być znaleziona najcięższa ryba.
     * @return Obiekt Fish reprezentujący najcięższą rybę dla danego użytkownika.
     */
    @Query(value = "SELECT * FROM fishes f " +
            "JOIN fishing_sessions fs ON f.session_id = fs.id " +
            "JOIN users u ON fs.user_id = u.id " +
            "WHERE u.id = :userId " +
            "ORDER BY f.weight DESC " +
            "LIMIT 1", nativeQuery = true)
    Fish findHeaviestFishForUser(@Param("userId") Long userId);

}
