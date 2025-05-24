package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.entities.TrackingRecord;
import co.simplon.everydaybetterbusiness.repositories.TrackingRecordRepository;
import co.simplon.everydaybetterbusiness.services.TrackingRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
    public List<Map<LocalDate, Boolean>> findTrackingByDayList(final Long activityId, final LocalDate startDate, final LocalDate endDate ) {
        return repository.findAllByActivityId(activityId, startDate, endDate).stream()
                .map(trackingDto -> Map.of(trackingDto.getTrackedDate(), trackingDto.getDone())).toList();
    }
}
