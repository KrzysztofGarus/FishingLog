package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.someday.FishingApp.model.FishName;
import pl.someday.FishingApp.model.FishingSpot;

import java.util.NoSuchElementException;

/**
 * Interfejs repozytorium, który umożliwia dostęp do danych związanych z nazwami ryb.
 */

public interface FishNameRepository extends JpaRepository<FishName, Long> {

    /**
     * Pobiera obiekt FishName na podstawie jego identyfikatora lub wyrzuca wyjątek, jeśli nie zostanie znaleziony.
     *
     * @param id Identyfikator FishName do pobrania.
     * @return Znaleziony obiekt FishName.
     * @throws NoSuchElementException jeśli nie zostanie znaleziony obiekt FishName o podanym identyfikatorze.
     */
    default FishName findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nie znaleziono Ryby o ID: " + id));
    }

}

