package pl.someday.FishingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.someday.FishingApp.model.FishName;

@Repository
public interface FishNameRepository extends JpaRepository<FishName, Long> {

    FishName getFishNameById(Long id);

}

