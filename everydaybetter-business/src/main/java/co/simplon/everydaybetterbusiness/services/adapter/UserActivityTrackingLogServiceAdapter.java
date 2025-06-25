package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.TrackingLogCreate;
import co.simplon.everydaybetterbusiness.dtos.TrackingLogUpdate;
import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.entities.TrackingLog;
import co.simplon.everydaybetterbusiness.mappers.TrackingLogMapper;
import co.simplon.everydaybetterbusiness.models.ActivityTrackingLogModel;
import co.simplon.everydaybetterbusiness.models.TrackingLogModel;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import co.simplon.everydaybetterbusiness.services.TrackingLogService;
import co.simplon.everydaybetterbusiness.services.UserActivityTrackingLogService;
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
    public TrackingLogModel saveTrackingLogForUserActivity(final TrackingLogCreate inputs, final String email){
        final TrackingLog entity = new TrackingLog();
        final Activity activity = activityService.findByIdAndUserEmail(Long.valueOf(inputs.activityId()), email);

        entity.setActivity(activity);
        entity.setTrackedDate(inputs.trackedDate());
        entity.setDone(inputs.done());
        return TrackingLogMapper.toModel(trackingLogService.save(entity));
    }

    @Override
    public List<ActivityTrackingLogModel> getTrackingActivityByDay(final LocalDate startDate, final LocalDate endDate, final String email) {
        return activityService.findAllActivitiesByUserEmail(email)
                .stream()
                .map(activity -> new ActivityTrackingLogModel(activity.getId(), activity.getName(), getTrackingByDayList(activity.getId(), startDate, endDate)))
                .toList();
    }

    @Override
    public void deleteActivityById(final Long id, final String email) {
        //verify user token and user activity
        trackingLogService.deleteAllByActivityId(id);
        activityService.delete(id);
    }

    @Override
    public void updateTrackingActivity(final TrackingLogUpdate trackingLogUpdate,final String email) {
        if (activityService.existByActivityIdAndUserEmail(Long.valueOf(trackingLogUpdate.activityId()), email)){
            trackingLogService.updateTrackingActivity(trackingLogUpdate);
        }else {
            throw new BadCredentialsException("User can't update this activity");
        }
    }

    private List<ActivityTrackingLogModel.TrackingLogDto> getTrackingByDayList(final Long activityId, final LocalDate startDate, final  LocalDate endDate ) {
        return trackingLogService.findAllTrackingLogByActivityIdAndPeriodTime(activityId, startDate, endDate);
    }
}
