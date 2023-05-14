package pl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.model.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long>{
    Visitor findById(long id);
}
