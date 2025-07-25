package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.ActivityCreate;
import co.simplon.everydaybetterbusiness.dtos.ActivityUpdate;
import co.simplon.everydaybetterbusiness.models.ActivityDetailModel;
import co.simplon.everydaybetterbusiness.models.ActivityModel;

import java.util.List;

public interface ActivityManagerService {
    ActivityModel create(ActivityCreate inputs, String email);

    List<ActivityModel> getAllActivitiesByUser(String email);

    ActivityDetailModel findById(Long id, String email);

    void update(Long id, ActivityUpdate inputs, String email);
}
