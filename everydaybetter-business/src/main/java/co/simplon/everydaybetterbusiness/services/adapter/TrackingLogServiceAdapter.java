package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.TrackingLogUpdate;
import co.simplon.everydaybetterbusiness.entities.TrackingLog;
import co.simplon.everydaybetterbusiness.exceptions.ResourceNotFoundException;
import co.simplon.everydaybetterbusiness.models.ActivityTrackingLogModel;
import co.simplon.everydaybetterbusiness.repositories.TrackingLogRepository;
import co.simplon.everydaybetterbusiness.services.TrackingLogService;
import co.simplon.everydaybetterbusiness.view.TrackingSummaryView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackingLogServiceAdapter implements TrackingLogService {
    private static final Logger logger = LoggerFactory.getLogger(TrackingLogServiceAdapter.class);
    private final TrackingLogRepository repository;

    public TrackingLogServiceAdapter(TrackingLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public TrackingLog save(final TrackingLog trackingLog) {
        return repository.save(trackingLog);
    }

    @Override
    public List<ActivityTrackingLogModel.TrackingLogDto> findAllTrackingLogByActivityIdAndPeriodTime(final Long activityId, final LocalDate startDate, final LocalDate endDate) {
        List<ActivityTrackingLogModel.TrackingLogDto> listTrackingLog = new ArrayList<>();
        repository.findAllTrackingLogByActivityIdAndPeriodTime(activityId, startDate, endDate)
                .forEach(trackingView -> listTrackingLog.add(new ActivityTrackingLogModel.TrackingLogDto(trackingView.getId(), trackingView.getTrackedDate(), trackingView.getDone())));
        return listTrackingLog;
    }

    @Transactional
    @Override
    public void updateTrackingActivity(final TrackingLogUpdate trackingLogUpdate) {

        TrackingLog trackingLog = repository.findByTrackedDayAndActivityId(trackingLogUpdate.trackedDate(), trackingLogUpdate.activityId()).orElseThrow(() -> new ResourceNotFoundException("Tracking log Not found"));
        trackingLog.setDone(trackingLogUpdate.done());
        repository.save(trackingLog);
    }

    @Override
    public void deleteById(final Long id, final String email) {
        if (!verifyExistTrackingLogById(id)) {
            logger.error("Error deleting this tracking log: with id not found");
            throw new ResourceNotFoundException("Tracking log with id " + id + " not found");
        }
        if (!verifyUserRightToRemoveTrackingLog(id, email)) {
            logger.error("Error deleting this tracking log with user right");
            throw new BadCredentialsException("User can't delete this tracking log");
        }
        repository.deleteById(id);
    }

    private boolean verifyExistTrackingLogById(final Long id) {
        return repository.existsById(id);
    }

    private boolean verifyUserRightToRemoveTrackingLog(final Long id, final String email) {
        return repository.existsByIdAndUserEmail(id, email);
    }

    @Override
    @Transactional
    public void deleteAllByActivityId(final Long activityId) {
        repository.deleteAllByActivityId(activityId);
    }

    @Override
    public TrackingSummaryView findTrackingSummaryByActivityIdAndPeriod(final Long activityId, final LocalDate startDate, final LocalDate endDate) {
        return repository.findTrackingSummaryByActivityIdAndPeriod(activityId, startDate, endDate);
    }

    @Override
    public boolean existsTrackingLogByActivityIdAndPeriod(final Long activityId, final LocalDate startDate, final LocalDate endDate) {
        return repository.existsTrackingLogByActivityIdAndPeriod(activityId, startDate, endDate);
    }
}
