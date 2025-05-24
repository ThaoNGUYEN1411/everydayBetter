package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.repositories.ActivityRepository;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import org.hibernate.service.spi.ServiceException;
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
    public List<Activity> findByUserId(final Long id) {
        return activityRepository.findByUserId(id);
        //no need new ServiceException("Activity not found with id"+id) juste return empty list
    }
//TODO hanlde ex!!!!!!!!
    @Override
    public Activity findById(Long id) {
//       return Optional.ofNullable(id).ifPresent(activityRepository::findById).orElseThrow(() -> new RuntimeException("Activity with ID " + id + " not found"));
        return activityRepository.findById(id).orElseThrow(()->new ServiceException("Activity with ID " + id + " not found")   );
//        return activityRepository.findById(id).orElseThrow(()->new Exception("Activity with ID " + id + " not found")   );

//        Optional.of(activityRepository::findById)
    }

    @Override
    public void delete(final Long id){
        activityRepository.deleteById(id);
    }

    @Override
    public List<Activity> findAllActivitiesByUserEmail(final String email) {
        return activityRepository.findAllActivitiesByUserEmail(email);
    }
}

//Note: LocalDate.now() => return date
// Instant.now() => return un instant date + time
