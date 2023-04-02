package com.helloworld.respository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.helloworld.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
    Team findById(long id);
}
