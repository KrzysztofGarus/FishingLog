package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.someday.FishingApp.model.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {

    Fish getFishById(Long id);
    void delete(Fish fish);

}
