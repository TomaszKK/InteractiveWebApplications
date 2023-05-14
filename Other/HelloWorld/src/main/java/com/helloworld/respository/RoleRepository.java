package com.helloworld.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.helloworld.model.Role;
import com.helloworld.model.RoleName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>  {
    Optional<Role> findByName(RoleName roleName);
}
