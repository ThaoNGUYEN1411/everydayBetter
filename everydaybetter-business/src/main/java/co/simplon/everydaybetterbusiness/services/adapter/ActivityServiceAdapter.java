package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.exceptions.ResourceNotFoundException;
import co.simplon.everydaybetterbusiness.repositories.ActivityRepository;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import co.simplon.everydaybetterbusiness.view.ActivityView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceAdapter implements ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityServiceAdapter(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Activity save(final Activity entity) {
        return activityRepository.save(entity);
    }

    @Override
    public List<Activity> findByUserId(final Long id) {
        return activityRepository.findByUserId(id);
    }

    @Override
    public Activity findByIdAndUserEmail(final Long id, final String email) {
        return activityRepository.findByIdAndUserEmail(id, email).orElseThrow(() -> new ResourceNotFoundException("Activity with ID " + id + " not found"));
    }

    @Override
    public void delete(final Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public List<ActivityView> findAllActivitiesByUserEmail(final String email) {
        return activityRepository.findAllActivitiesByUserEmail(email);
    }

    @Override
    public boolean existByActivityIdAndUserEmail(final Long activityId, final String email) {
        return activityRepository.existByActivityIdAndUserEmail(activityId, email);
    }

    @Override
    public Activity findById(Long id) {
        return activityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found activity with id :" + id));
    }
}

//Note: LocalDate.now() => return date
// Instant.now() => return un instant date + time
