package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.view.ActivityView;

import java.util.List;

public interface ActivityService {

    Activity save(Activity entity);

    List<Activity> findByUserId(Long id);

    Activity findByIdAndUserEmail(Long id, String email);

    void delete(Long id);

    List<ActivityView> findAllActivitiesByUserEmail(String email);

    boolean existByActivityIdAndUserEmail(Long activityId, String email);

    Activity findById(Long id);
}
