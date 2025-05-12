package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.TrackingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface TrackingRecordRepository extends JpaRepository<TrackingRecord, Long> {
    @Query(
            """ 
                    select count(tr) > 0 from TrackingRecord tr
                    Where tr.activity.id = :activityId
                    and tr.trackedDate = :trackedDate
                    """
    )
    boolean existsByActivityIdAndTrackedDate(@Param("activityId") Long activityId,@Param("trackedDate") LocalDate trackedDate);
}
