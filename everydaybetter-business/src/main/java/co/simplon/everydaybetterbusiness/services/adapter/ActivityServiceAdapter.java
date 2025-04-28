package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.exceptions.NotFoundException;
import co.simplon.everydaybetterbusiness.repositories.ActivityRepository;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceAdapter implements ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityServiceAdapter(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void save(Activity entity) {
        activityRepository.save(entity);
    }

    @Override
    public List<Activity> findByUserId(Long id) {
        return activityRepository.findByUserId(id).orElseThrow(()-> new NotFoundException("Activity with ID not found"));
    }

    @Override
    public Activity findById(Long id) {
        return activityRepository.findById(id).orElseThrow(()->new NotFoundException("Activity with ID " + id + " not found")   );
    }

    @Override
    public void delete(final Long id){
        activityRepository.deleteById(id);
    }
}

//Note: LocalDate.now() => return date
// Instant.now() => return un instant date + time