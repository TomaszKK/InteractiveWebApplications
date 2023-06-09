package pl.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.model.Artist;
import pl.model.User;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
    Artist findById(long id);
    Artist findByUser(User user);
}
