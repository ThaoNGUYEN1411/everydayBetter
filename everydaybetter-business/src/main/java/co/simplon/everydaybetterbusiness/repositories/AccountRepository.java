package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

   Optional<AccountEntity> findAllByEmailIgnoreCase(String email);

   Optional<AccountEntity> findByUsernameIgnoreCase(String username);

    Optional<AccountEntity> findAllByUsernameIgnoreCase(String username);
}
//Todo: return optinal