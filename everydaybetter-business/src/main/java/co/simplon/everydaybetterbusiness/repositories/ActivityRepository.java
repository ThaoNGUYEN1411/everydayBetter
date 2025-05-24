package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByUserId(Long id);

    boolean existsByNameIgnoreCaseAndUserId(String name, Long userId);

    @Query(value = """
            select a from Activity a
            where a.user.email = :email
            """)
    List<Activity> findAllActivitiesByUserEmail(@Param(value = "email") String email);
    //todo return a dto includes necessary fields
}
