package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.TrackingLog;
import co.simplon.everydaybetterbusiness.view.TrackingSummaryView;
import co.simplon.everydaybetterbusiness.view.TrackingView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    boolean existsByActivityIdAndTrackedDate(@Param("activityId") Long activityId, @Param("trackedDate") LocalDate trackedDate);

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
    List<TrackingView> findAllTrackingLogByActivityIdAndPeriodTime(@Param(value = "activityId") Long activityId,
            @Param(value = "startDate") LocalDate startDate,
            @Param(value = "endDate") LocalDate endDate);

    @Transactional
    @Query(value = """
            select t from TrackingLog t
            where t.trackedDate = :trackedDate
            and t.activity.id = :activityId
            """)
    Optional<TrackingLog> findByTrackedDayAndActivityId(@Param(value = "trackedDate") LocalDate trackedDate, @Param(value = "activityId") String activityId);

    @Modifying
    @Query(value = """
            delete from TrackingLog t
            where t.activity.id = :activityId
            """)
    void deleteAllByActivityId(@Param(value = "activityId") Long activityId);

    @Query(value = """
            SELECT
            sum(CASE WHEN done IS TRUE THEN 1 ELSE 0 END ) AS sumDone,
            sum(CASE WHEN done IS FALSE THEN 1 ELSE 0 END ) AS sumMissed
            FROM t_tracking_logs t WHERE t.activity_id = :activityId
            and t.tracked_date >= :startDate
            and t.tracked_date <= :endDate;
            """, nativeQuery = true)
    TrackingSummaryView findTrackingSummaryByActivityIdAndPeriod(Long activityId, LocalDate startDate, LocalDate endDate);

    @Query(value = """
            select case when count(t)> 0 then true else false end
            from TrackingLog t
            where t.id = :id
            and t.activity.user.email = :email
            """)
    boolean existsByIdAndUserEmail(@Param(value = "id") Long id, @Param(value = "email") String email);

    @Query(value = """
            select count(t)>0 from TrackingLog t
                    Where t.activity.id = :activityId
                    and t.trackedDate >= :startDate
                    and t.trackedDate <= :endDate
            """)
    boolean existsTrackingLogByActivityIdAndPeriod(Long activityId, LocalDate startDate, LocalDate endDate);
}
