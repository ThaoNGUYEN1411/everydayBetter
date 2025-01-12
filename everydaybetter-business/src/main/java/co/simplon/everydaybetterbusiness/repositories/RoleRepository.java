package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository <RoleEntity, Long> {
    RoleEntity findByRoleName(String role);

    Optional<Set<RoleEntity>> findByRoleDefaultTrue();
}
