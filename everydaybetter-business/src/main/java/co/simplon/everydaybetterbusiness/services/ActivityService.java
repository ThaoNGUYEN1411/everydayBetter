package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.input.ActivityCreate;
import co.simplon.everydaybetterbusiness.dtos.input.ActivityUpdate;
import co.simplon.everydaybetterbusiness.dtos.output.ActivityDto;
import co.simplon.everydaybetterbusiness.models.ActivityModel;
import jakarta.validation.Valid;

import java.util.List;

public interface ActivityService {
    void create(final ActivityCreate inputs);

    List<ActivityDto> getAllActivitiesByUser();

    ActivityModel findById(final Long id);

    void delete(final Long id);

    void update(Long id, @Valid ActivityUpdate inputs);
}
