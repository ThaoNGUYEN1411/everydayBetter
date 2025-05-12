package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.TrackingRecordDto;
import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.entities.TrackingRecord;
import co.simplon.everydaybetterbusiness.mappers.TrackingRecordMapper;
import co.simplon.everydaybetterbusiness.models.TrackingRecordModel;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import co.simplon.everydaybetterbusiness.services.TrackingRecordForUserActivityService;
import co.simplon.everydaybetterbusiness.services.TrackingRecordService;
import org.springframework.stereotype.Service;

@Service
public class TrackingRecordForUserActivityServiceAdapter implements TrackingRecordForUserActivityService {
    private final TrackingRecordService trackingRecordService;
    private final ActivityService activityService;

    public TrackingRecordForUserActivityServiceAdapter(TrackingRecordService trackingRecordService, ActivityService activityService) {
        this.trackingRecordService = trackingRecordService;
        this.activityService = activityService;
    }

    @Override
    public TrackingRecordModel saveTrackingRecordForUserActivity(final TrackingRecordDto inputs, final String email){
        final TrackingRecord entity = new TrackingRecord();
        Activity activity = activityService.findById(Long.valueOf(inputs.activityId()));

        entity.setActivity(activity);
        entity.setTrackedDate(inputs.trackedDate());
        entity.setDone(inputs.done());
        // Vérifier l'existence de l’utilisateur et de l’activité


        //import toModel avec nom de class car on utiliser method static
        return TrackingRecordMapper.toModel(trackingRecordService.save(entity));
    }
}
