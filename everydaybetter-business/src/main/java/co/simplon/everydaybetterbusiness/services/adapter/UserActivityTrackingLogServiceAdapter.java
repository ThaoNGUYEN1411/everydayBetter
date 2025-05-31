package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.TrackingLogCreate;
import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.entities.TrackingLog;
import co.simplon.everydaybetterbusiness.mappers.TrackingLogMapper;
import co.simplon.everydaybetterbusiness.models.ActivityTrackingLogModel;
import co.simplon.everydaybetterbusiness.models.TrackingLogModel;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import co.simplon.everydaybetterbusiness.services.TrackingLogService;
import co.simplon.everydaybetterbusiness.services.UserActivityTrackingLogService;
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
        Activity activity = activityService.findById(Long.valueOf(inputs.activityId()));

        entity.setActivity(activity);
        entity.setTrackedDate(inputs.trackedDate());
        entity.setDone(inputs.done());
        // Vérifier l'existence de l’utilisateur et de l’activité
        //import toModel avec nom de class car on utiliser method static
        return TrackingLogMapper.toModel(trackingLogService.save(entity));
    }

    @Override
    public List<ActivityTrackingLogModel> getTrackingActivityByDay(final LocalDate startDate, final LocalDate endDate, final String email) {

        return activityService.findAllActivitiesByUserEmail(email)
                .stream()
                .map(activity -> new ActivityTrackingLogModel(activity.getId(), activity.getName(), getTrackingByDayList(activity.getId(), startDate, endDate)))
                .toList();
    }

//    @Override
//    public void updateTrackingActivity(final TrackingRecordUpdate inputs) {
//        trackingRecordService.findTrackingRecordByActivityIdAndTrackedDate(inputs);
//    }

    private List<ActivityTrackingLogModel.TrackingLogDto> getTrackingByDayList(final Long activityId, final LocalDate startDate, final  LocalDate endDate ) {
        return trackingLogService.findAllTrackingLogByActivityIdAndPeriodTime(activityId, startDate, endDate);
    }
}
