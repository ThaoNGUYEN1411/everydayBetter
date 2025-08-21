package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.view.ActivityView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query(value = """
            select a.id as id,
                   a.name as name
            from Activity a
            where a.user.email = :email
            """)
    List<ActivityView> findAllActivitiesByUserEmail(@Param(value = "email") String email);

    boolean existsByNameIgnoreCaseAndUserId(String name, Long userId);

    boolean existsByNameIgnoreCaseAndUserIdAndIdNot(String name, Long userId, Long id);

    @Query(value = """
            select a
            from Activity a
            where a.id = :activityId
            and a.user.email = :email
            """)
    Optional<Activity> findByIdAndUserEmail(@Param(value = "activityId") Long activityId, @Param(value = "email") String email);

    List<Activity> findByUserId(Long id);


    @Query(value = """
            select count(a)>0
            from Activity a
            where a.id = :activityId
            and a.user.email= :email
            """)
    boolean existByActivityIdAndUserEmail(@Param(value = "activityId") Long activityId, @Param(value = "email") String email);
}
