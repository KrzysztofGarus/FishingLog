package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.someday.FishingApp.model.User;

/**
 * Interfejs repozytorium do obsługi operacji bazodanowych na encji User.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Pobiera obiekt User na podstawie nazwy użytkownika.
     *
     * @param username Nazwa użytkownika.
     * @return Znaleziony obiekt User.
     */
    User findByUsername(String username);

    /**
     * Zlicza aktywnych użytkowników w bazie danych.
     *
     * @return Liczba aktywnych użytkowników.
     */
    @Query("SELECT COUNT(u) FROM User u WHERE u.active = true")
    Long countActiveUsers();
}
