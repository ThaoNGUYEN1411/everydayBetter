package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Set<Category>> findByNameIgnoreCase(String name);
}
//todo: return optional