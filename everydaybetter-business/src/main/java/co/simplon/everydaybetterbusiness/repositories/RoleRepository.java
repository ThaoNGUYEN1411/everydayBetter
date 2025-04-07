package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
    Role findByName(String role);

    Optional<Set<Role>> findByRoleDefaultTrue();
}
