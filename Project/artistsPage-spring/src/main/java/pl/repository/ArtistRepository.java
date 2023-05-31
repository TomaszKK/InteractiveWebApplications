package pl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
    Artist findById(long id);
}
