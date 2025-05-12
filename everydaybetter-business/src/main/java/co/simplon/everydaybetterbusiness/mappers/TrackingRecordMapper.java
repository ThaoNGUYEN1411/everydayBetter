package co.simplon.everydaybetterbusiness.mappers;

import co.simplon.everydaybetterbusiness.entities.TrackingRecord;
import co.simplon.everydaybetterbusiness.models.TrackingRecordModel;

public final class TrackingRecordMapper {
    public static TrackingRecordModel toModel(TrackingRecord entity){
        return new TrackingRecordModel(entity.getActivity().getId().toString(), entity.getTrackedDate(), entity.getDone());
    }
}
