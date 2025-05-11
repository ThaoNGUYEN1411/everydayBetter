package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.entities.TrackingRecord;
import co.simplon.everydaybetterbusiness.repositories.TrackingRecordRepository;
import co.simplon.everydaybetterbusiness.services.TrackingRecordService;
import org.springframework.stereotype.Service;

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
}
