package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.controllers.TrackingLogController;
import co.simplon.everydaybetterbusiness.dtos.TrackingLogCreate;
import co.simplon.everydaybetterbusiness.dtos.TrackingLogUpdate;
import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.entities.TrackingLog;
import co.simplon.everydaybetterbusiness.mappers.TrackingLogMapper;
import co.simplon.everydaybetterbusiness.models.ActivitiesProgressAnalyticsModel;
import co.simplon.everydaybetterbusiness.models.ActivityTrackingLogModel;
import co.simplon.everydaybetterbusiness.models.TrackingLogModel;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import co.simplon.everydaybetterbusiness.services.TrackingLogService;
import co.simplon.everydaybetterbusiness.services.UserActivityTrackingLogService;
import co.simplon.everydaybetterbusiness.view.ActivityView;
import co.simplon.everydaybetterbusiness.view.TrackingSummaryView;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class UserActivityTrackingLogServiceAdapter implements UserActivityTrackingLogService {
    private static final Logger log = LoggerFactory.getLogger(TrackingLogController.class);

    private final TrackingLogService trackingLogService;
    private final ActivityService activityService;

    public UserActivityTrackingLogServiceAdapter(TrackingLogService trackingLogService, ActivityService activityService) {
        this.trackingLogService = trackingLogService;
        this.activityService = activityService;
    }

    @Override
    public TrackingLogModel saveTrackingLogForUserActivity(final TrackingLogCreate inputs, final String email) {
        final TrackingLog entity = new TrackingLog();
        final Activity activity = activityService.findByIdAndUserEmail(Long.valueOf(inputs.activityId()), email);

        entity.setActivity(activity);
        entity.setTrackedDate(inputs.trackedDate());
        entity.setDone(inputs.done());
        return TrackingLogMapper.toModel(trackingLogService.save(entity));
    }

    @Override
    public List<ActivityTrackingLogModel> getTrackingActivityByDay(final LocalDate startDate, final LocalDate endDate, final String email) {
        return activityService.findAllActivitiesByUserEmail(email).stream()
                .map(activity -> new ActivityTrackingLogModel(activity.getId(), activity.getName(), getTrackingByDayList(activity.getId(), startDate, endDate))).toList();
    }

    private List<ActivityTrackingLogModel.TrackingLogDto> getTrackingByDayList(final Long activityId, final LocalDate startDate, final LocalDate endDate) {
        return trackingLogService.findAllTrackingLogByActivityIdAndPeriodTime(activityId, startDate, endDate);
    }

    @Override
    public void deleteActivityById(final Long id, final String email) {
        //verify user token and user activity
        trackingLogService.deleteAllByActivityId(id);
        activityService.delete(id);
    }

    @Override
    public void updateTrackingActivity(final TrackingLogUpdate trackingLogUpdate, final String email) {
        if (activityService.existByActivityIdAndUserEmail(Long.valueOf(trackingLogUpdate.activityId()), email)) {
            trackingLogService.updateTrackingActivity(trackingLogUpdate);
        } else {
            log.error("User can't update this activity");
            throw new BadCredentialsException("User can't update this activity");
        }
    }

    @Override
    public List<ActivitiesProgressAnalyticsModel> getActivitiesProgressAnalytics(final LocalDate startDate, final LocalDate endDate, final String email) {
        verifyStartAndEndDate(startDate, endDate);
        final List<ActivityView> activityViewList = activityService.findAllActivitiesByUserEmail(email);
        LocalDate effectiveStartDate = getStartDate(startDate, endDate);
        LocalDate effectiveEndDate = getEndDate(endDate);
        return activityViewList.stream().filter(a -> trackingLogService.existsTrackingLogByActivityIdAndPeriod(a.getId(), effectiveStartDate, effectiveEndDate))
                .map(a -> new ActivitiesProgressAnalyticsModel(a.getId(), a.getName(), buildProgress(a.getId(), effectiveStartDate, effectiveEndDate))).toList();
    }

    private void verifyStartAndEndDate(final LocalDate startDate, final LocalDate endDate) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new ServiceException("StartDate cannot be after endDate");
        }
    }

    private LocalDate getStartDate(final LocalDate startDate, final LocalDate endDate) {
        if (startDate != null) return startDate;
        if (endDate != null) return endDate.minusMonths(1);
        return LocalDate.now().minusMonths(1);
    }

    private LocalDate getEndDate(final LocalDate endDate) {
        return (endDate == null || endDate.isAfter(LocalDate.now())) ? LocalDate.now() : endDate;
    }

    private ActivitiesProgressAnalyticsModel.Progress buildProgress(final Long activityId, final LocalDate startDate, final LocalDate endDate) {
        TrackingSummaryView trackingSummaryView = trackingLogService.findTrackingSummaryByActivityIdAndPeriod(activityId, startDate, endDate);
        long sumDone = trackingSummaryView.getSumDone();
        long sumMissed = trackingSummaryView.getSumMissed();
        long total = ChronoUnit.DAYS.between(startDate, endDate);
        //using the Math class to remove extra decimal places Math.floor(positive * 100) / 100;
        double percentDone = total > 0 ? Math.floor((double) (sumDone * 100) * 100 / total) / 100 : 0;
        double percentMissed = total > 0 ? Math.floor((double) (sumMissed * 100) * 100 / total) / 100 : 0;
        double percentUntracked = 100 - (percentDone + percentMissed);
        return new ActivitiesProgressAnalyticsModel.Progress(percentDone, percentMissed, percentUntracked);
    }
}
