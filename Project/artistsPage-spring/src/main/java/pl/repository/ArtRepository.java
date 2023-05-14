package pl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.model.Art;

public interface ArtRepository extends JpaRepository<Art, Long>{
    Art findById(long id);
}
