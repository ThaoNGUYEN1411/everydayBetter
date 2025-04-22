package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.repositories.ActivityRepository;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceAdapter implements ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityServiceAdapter(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

//    @Override
//    public void create(final ActivityCreate inputs){
//        Activity entity = new Activity();
//        entity.setName(inputs.name());
//        entity.setDescription(inputs.description());
//        entity.setPositive(inputs.positive());
//
//        String email = utils.getAuthenticatedUser();
//        User user = userRepository.findByEmailIgnoreCase(email)
//                .orElseThrow(() -> new BadCredentialsException(email));
//        entity.setUser(user);
//
//        String categoryId = inputs.categoryId();
//        saveCategory(categoryIds, entity);
//
//        activityRepository.save(entity);
//    }

    @Override
    public void save(Activity entity) {
        activityRepository.save(entity);
    }

//    private void saveCategory(String categoryIds, Activity entity) {
//        if (categoryIds != null){
//            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(Collections.singleton(Long.parseLong(categoryIds))));
//            entity.setCategories(categories);
//        }else {
//            entity.setCategories(null);
//        }
//    }

//    @Override
//    public List<ActivityDto> getAllActivitiesByUser(){
//        String email = utils.getAuthenticatedUser();
//        User user = userRepository.findByEmailIgnoreCase(email)
//                .orElseThrow(() -> new BadCredentialsException(email));
//        List<Activity> activities = activityRepository.findByUserId(user.getId()).orElseThrow(()-> new NotFoundException("Activity with ID not found"));
//
//        return activities.stream().map(act -> new ActivityDto(act.getId(), act.getName(), act.getPositive())).toList();
//    }
//
//    @Override
//    public ActivityModel findById(final Long id){
//        Activity entity = activityRepository.findById(id).orElseThrow(() -> new NotFoundException("Activity with ID " + id + " not found"));
//        return new ActivityModel(entity.getId(), entity.getName(), entity.getDescription(), entity.getPositive(),
//                entity.getCategories().stream().map(c -> new ActivityModel.Category(c.getId(), c.getName())).toList());
//    }
//
//    @Override
//    public void delete(final Long id){
//        activityRepository.deleteById(id);
//    }
//
//    @Override
//    public void update(Long id, @Valid ActivityUpdate inputs){
//        Activity entity = activityRepository.findById(id).orElseThrow(()-> new NotFoundException("Activity with ID " + id + " not found"));
//
//        entity.setName(inputs.name());
//        entity.setDescription(inputs.description());
//        entity.setPositive(inputs.positive());
//
//        List<Long> categoryIds = inputs.categoryIds();
//        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoryIds));
//        entity.setCategories(categories);
//
//        activityRepository.save(entity);
//    }

}

//Note: LocalDate.now() => return date
// Instant.now() => return un instant date + time