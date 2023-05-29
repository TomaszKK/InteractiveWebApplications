package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.model.Role;
import pl.model.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
