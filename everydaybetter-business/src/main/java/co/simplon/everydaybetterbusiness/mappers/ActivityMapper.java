package co.simplon.everydaybetterbusiness.mappers;

import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.models.ActivityModel;

public final class ActivityMapper {
    public static ActivityModel toModel(Activity entity) {
        return new ActivityModel(entity.getId(), entity.getName(), entity.getPositive());
    }
}
