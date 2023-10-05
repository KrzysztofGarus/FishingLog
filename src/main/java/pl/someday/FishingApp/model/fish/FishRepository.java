package pl.someday.FishingApp.model.fish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {

    Fish getFishById(Long id);
    void delete(Fish fish);

}
