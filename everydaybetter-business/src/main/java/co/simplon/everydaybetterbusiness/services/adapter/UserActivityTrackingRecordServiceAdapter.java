package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.TrackingRecordCreate;
import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.entities.TrackingRecord;
import co.simplon.everydaybetterbusiness.mappers.TrackingRecordMapper;
import co.simplon.everydaybetterbusiness.models.ActivityTrackingRecordModel;
import co.simplon.everydaybetterbusiness.models.TrackingRecordModel;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import co.simplon.everydaybetterbusiness.services.TrackingRecordService;
import co.simplon.everydaybetterbusiness.services.UserActivityTrackingRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class UserActivityTrackingRecordServiceAdapter implements UserActivityTrackingRecordService {
    private final TrackingRecordService trackingRecordService;
    private final ActivityService activityService;

    public UserActivityTrackingRecordServiceAdapter(TrackingRecordService trackingRecordService, ActivityService activityService) {
        this.trackingRecordService = trackingRecordService;
        this.activityService = activityService;
    }

    @Override
    public TrackingRecordModel saveTrackingRecordForUserActivity(final TrackingRecordCreate inputs, final String email){
        final TrackingRecord entity = new TrackingRecord();
        Activity activity = activityService.findById(Long.valueOf(inputs.activityId()));

        entity.setActivity(activity);
        entity.setTrackedDate(inputs.trackedDate());
        entity.setDone(inputs.done());
        // Vérifier l'existence de l’utilisateur et de l’activité
        //import toModel avec nom de class car on utiliser method static
        return TrackingRecordMapper.toModel(trackingRecordService.save(entity));
    }

    @Override
    public List<ActivityTrackingRecordModel> getTrackingActivityByDay(final LocalDate startDate, final LocalDate endDate, final String email) {
        return activityService.findAllActivitiesByUserEmail(email)
                .stream()
                .map(activity -> new ActivityTrackingRecordModel(activity.getId(), activity.getName(), getTrackingByDayList(activity.getId(), startDate, endDate)))
                .toList();
    }

//    @Override
//    public void updateTrackingActivity(final TrackingRecordUpdate inputs) {
//        trackingRecordService.findTrackingRecordByActivityIdAndTrackedDate(inputs);
//    }

    private Map<LocalDate, Boolean> getTrackingByDayList(final Long activityId, final LocalDate startDate, final  LocalDate endDate ) {
        return trackingRecordService.findTrackingByDayList(activityId, startDate, endDate);
    }
}
