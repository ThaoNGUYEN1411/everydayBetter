package co.simplon.everydaybetterbusiness.services.adapter;

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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserActivityTrackingLogServiceAdapter implements UserActivityTrackingLogService {

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
            throw new BadCredentialsException("User can't update this activity");
        }
    }

    @Override
    public List<ActivitiesProgressAnalyticsModel> getActivitiesProgressAnalytics(LocalDate startDate, LocalDate endDate, final String email) {
        //verify endDate>=startDate
        if ((endDate == null) || (endDate.isAfter(LocalDate.now()))) {
            endDate = LocalDate.now();
        }
        if (startDate == null) {
            startDate = LocalDate.now().minusMonths(1);
        }
        final LocalDate finalStartDate = startDate;
        final LocalDate finalEndDate = endDate;

        final List<ActivityView> activityViewList = activityService.findAllActivitiesByUserEmail(email);
        return activityViewList.stream().map(a -> new ActivitiesProgressAnalyticsModel(a.getId(), a.getName(), buildProgress(a.getId(), finalStartDate, finalEndDate))).toList();
    }

    private ActivitiesProgressAnalyticsModel.Progress buildProgress(Long activityId, LocalDate finalStartDate, LocalDate finalEndDate) {
        //if total = 0 return 0 else count percent
        if (!trackingLogService.existsTrackingLogByActivityIdAndPeriod(activityId, finalStartDate, finalEndDate)) {
            return new ActivitiesProgressAnalyticsModel.Progress(0, 0, 0);
        }

        TrackingSummaryView trackingSummaryView = trackingLogService.findTrackingSummaryByActivityIdAndPeriod(activityId, finalStartDate, finalEndDate);
        long sumDone = trackingSummaryView.getSumDone();
        long sumNotDone = trackingSummaryView.getSumNotDone();
        long sumNull = trackingSummaryView.getSumNull();
        long total = trackingSummaryView.getTotal();
        double percentDone = total > 0 ? (sumDone * 100) / total : 0;
        double percentNotDone = total > 0 ? (sumNotDone * 100) / total : 0;
        double percentNull = total > 0 ? (sumNull * 100) / total : 0;
        return new ActivitiesProgressAnalyticsModel.Progress(percentDone, percentNotDone, percentNull);
    }

    private List<ActivityTrackingLogModel.TrackingLogDto> getTrackingByDayList(final Long activityId, final LocalDate startDate, final LocalDate endDate) {
        return trackingLogService.findAllTrackingLogByActivityIdAndPeriodTime(activityId, startDate, endDate);
    }
}
