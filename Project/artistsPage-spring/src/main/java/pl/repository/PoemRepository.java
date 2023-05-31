package pl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.model.Poem;

@Repository
public interface PoemRepository extends JpaRepository<Poem, Long>{
    Poem findById(long id);
}
