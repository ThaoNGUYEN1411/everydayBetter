package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.input.ActivityCreate;
import co.simplon.everydaybetterbusiness.dtos.output.ActivityDto;

import java.util.List;

public interface ActivityService {
    void create(ActivityCreate inputs);

    List<ActivityDto> getAllActivities();
}
