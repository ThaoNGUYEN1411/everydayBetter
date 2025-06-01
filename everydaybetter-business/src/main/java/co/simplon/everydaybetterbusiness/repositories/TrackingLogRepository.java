package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.TrackingLog;
import co.simplon.everydaybetterbusiness.view.TrackingView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrackingLogRepository extends JpaRepository<TrackingLog, Long> {
    @Query(
            value = """ 
                    select count(t) > 0 from TrackingLog t
                    Where t.activity.id = :activityId
                    and t.trackedDate = :trackedDate
                    """
    )
    boolean existsByActivityIdAndTrackedDate(@Param("activityId") Long activityId,@Param("trackedDate") LocalDate trackedDate);

    @Query(value = """
            select t.id as id,
                   t.trackedDate as trackedDate,
                   t.done as done
                   from TrackingLog t
            where t.activity.id = :activityId
            and t.trackedDate >= :startDate
            and t.trackedDate <= :endDate
            order by t.trackedDate
            """)
    List<TrackingView> findAllTrackingLogByActivityIdAndPeriodTime(@Param(value = "activityId") Long activityId, @Param(value = "startDate")LocalDate startDate, @Param(value = "endDate") LocalDate endDate);

    @Transactional
    @Query(value = """
            select t from TrackingLog t
            where t.trackedDate = :trackedDate
            and t.activity.id = :activityId
            """)
    Optional<TrackingLog> findByTrackedDayAndActivityId(@Param(value = "trackedDate") LocalDate trackedDate, @Param(value = "activityId") String activityId);

    @Transactional
    @Query(value = """
            delete from TrackingLog t
            where t.activity.id = :activityId
            """)
    void deleteAllByActivityId(@Param(value = "activityId") Long activityId);

}
