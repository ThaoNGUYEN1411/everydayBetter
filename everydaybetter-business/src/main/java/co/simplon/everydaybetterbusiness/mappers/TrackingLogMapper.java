package co.simplon.everydaybetterbusiness.mappers;

import co.simplon.everydaybetterbusiness.entities.TrackingLog;
import co.simplon.everydaybetterbusiness.models.TrackingLogModel;

public final class TrackingLogMapper {
    public static TrackingLogModel toModel(TrackingLog entity){
        return new TrackingLogModel(entity.getActivity().getId().toString(), entity.getTrackedDate(), entity.getDone());
    }
}
