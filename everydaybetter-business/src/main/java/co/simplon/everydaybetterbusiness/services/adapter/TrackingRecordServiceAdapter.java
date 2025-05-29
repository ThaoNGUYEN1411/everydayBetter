package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.TrackingRecordDelete;
import co.simplon.everydaybetterbusiness.dtos.TrackingRecordUpdate;
import co.simplon.everydaybetterbusiness.entities.TrackingRecord;
import co.simplon.everydaybetterbusiness.exceptions.ResourceNotFoundException;
import co.simplon.everydaybetterbusiness.repositories.TrackingRecordRepository;
import co.simplon.everydaybetterbusiness.services.TrackingRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class TrackingRecordServiceAdapter implements TrackingRecordService {
    private final TrackingRecordRepository repository;

    public TrackingRecordServiceAdapter(TrackingRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public TrackingRecord save(final TrackingRecord trackingRecord) {
        return repository.save(trackingRecord);
    }

    @Override
    public Map<LocalDate, Boolean> findTrackingByDayList(final Long activityId, final LocalDate startDate, final LocalDate endDate ) {
        Map<LocalDate, Boolean> trackingByDay = new HashMap<>();
        repository.findAllByActivityId(activityId, startDate, endDate).forEach(trackingDto -> trackingByDay.put(trackingDto.getTrackedDate(), trackingDto.getDone()));
        return trackingByDay;
    }

    @Transactional
    @Override
    public void updateTrackingActivity(TrackingRecordUpdate inputs) {
        TrackingRecord trackingRecord = repository.findByTrackedDayAndActivityId(inputs.trackedDate(), inputs.activityId()).orElseThrow(()-> new ResourceNotFoundException("Tracking record Not found"));
        trackingRecord.setDone(inputs.done());
        repository.save(trackingRecord);
        System.out.println(trackingRecord);
    }

    @Override
    @Transactional
    public void deleteTrackingActivity(final TrackingRecordDelete trackingRecordDelete) {
        repository.deleteById(20L);
    }
}
