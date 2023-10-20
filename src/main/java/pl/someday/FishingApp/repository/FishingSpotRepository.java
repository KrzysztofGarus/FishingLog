package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.someday.FishingApp.model.FishingSpot;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Interfejs repozytorium, który umożliwia dostęp do danych związanych z miejscami połowu.
 */
@Repository
public interface FishingSpotRepository extends JpaRepository<FishingSpot, Long> {

    /**
     * Znajduje wszystkie miejsca połowu i sortuje je alfabetycznie według nazwy.
     *
     * @return Lista miejsc połowu.
     */
    List<FishingSpot> findAllByOrderByName();
    /**
     * Usuwa podane miejsce połowu.
     *
     * @param fishingSpot Miejsce połowu do usunięcia.
     */
    void delete(FishingSpot fishingSpot);

    /**
     * Pobiera miejsce połowu o podanym identyfikatorze. Jeżeli nie istnieje, rzuca wyjątek.
     *
     * @param id Identyfikator miejsca połowu.
     * @return Miejsce połowu o podanym identyfikatorze.
     * @throws NoSuchElementException Jeżeli nie znaleziono miejsca połowu o podanym identyfikatorze.
     */
    default FishingSpot findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nie znaleziono Spotu o ID: " + id));
    }

}
