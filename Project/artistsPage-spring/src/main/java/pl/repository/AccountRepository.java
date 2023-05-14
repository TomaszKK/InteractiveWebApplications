package pl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findById(long id);
}
