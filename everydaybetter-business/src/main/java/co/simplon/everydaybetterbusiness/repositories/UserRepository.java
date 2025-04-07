package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNicknameIgnoreCase(String nickname);

    Optional<User> findByEmailIgnoreCase(String email);

}
//Todo: return optinal