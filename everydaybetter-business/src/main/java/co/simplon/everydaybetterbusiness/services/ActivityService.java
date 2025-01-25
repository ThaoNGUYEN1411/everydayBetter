package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.input.ActivityCreate;
import co.simplon.everydaybetterbusiness.dtos.output.ActivityDto;
import co.simplon.everydaybetterbusiness.models.ActivityModel;

import java.util.List;

public interface ActivityService {
    void create(final ActivityCreate inputs);

    List<ActivityDto> getAllActivities();

    ActivityModel findById(final Long id);
}
