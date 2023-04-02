package com.helloworld.respository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.helloworld.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findById(long id);
}
