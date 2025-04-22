package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.ActivityCreate;
import co.simplon.everydaybetterbusiness.models.ActivityDetailModel;
import co.simplon.everydaybetterbusiness.models.ActivityModel;

import java.util.List;

public interface ActivityManagerService {
    void create(ActivityCreate inputs);

    List<ActivityModel> getAllActivitiesByUser();

    ActivityDetailModel findById(Long id);
}
