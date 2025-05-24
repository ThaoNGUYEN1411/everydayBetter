package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.TrackingRecord;
import co.simplon.everydaybetterbusiness.view.TrackingView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrackingRecordRepository extends JpaRepository<TrackingRecord, Long> {
    @Query(
            value = """ 
                    select count(tr) > 0 from TrackingRecord tr
                    Where tr.activity.id = :activityId
                    and tr.trackedDate = :trackedDate
                    """
    )
    boolean existsByActivityIdAndTrackedDate(@Param("activityId") Long activityId,@Param("trackedDate") LocalDate trackedDate);

    @Query(value = """
            select t.trackedDate as trackedDate,
                   t.done as done
                   from TrackingRecord t
            where t.activity.id = :activityId
            and t.trackedDate >= :startDate
            and t.trackedDate <= :endDate
            order by t.trackedDate
            """)
    List<TrackingView> findAllByActivityId(@Param(value = "activityId") Long activityId, @Param(value = "startDate")LocalDate startDate,@Param(value = "endDate") LocalDate endDate);
    //todo: it's better to return dto or a interface view
}
