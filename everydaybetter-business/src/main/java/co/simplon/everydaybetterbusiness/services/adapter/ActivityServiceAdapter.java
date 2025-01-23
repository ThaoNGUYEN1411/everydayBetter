package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.input.ActivityCreate;
import co.simplon.everydaybetterbusiness.dtos.output.ActivityDto;
import co.simplon.everydaybetterbusiness.entities.ActivityEntity;
import co.simplon.everydaybetterbusiness.entities.CategoryEntity;
import co.simplon.everydaybetterbusiness.entities.UserEntity;
import co.simplon.everydaybetterbusiness.repositories.ActivityRepository;
import co.simplon.everydaybetterbusiness.repositories.CategoryRepository;
import co.simplon.everydaybetterbusiness.repositories.UserRepository;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ActivityServiceAdapter implements ActivityService {
    private final ActivityRepository activityRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ActivityServiceAdapter(ActivityRepository activityRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void create(final ActivityCreate inputs){
        ActivityEntity entity = new ActivityEntity();

        entity.setName(inputs.name());
        entity.setDescription(inputs.description());
        entity.setPositive(inputs.positive());

        String categoryName = inputs.categoryName();
        Set<CategoryEntity> categoryEntity = categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new BadCredentialsException(categoryName));
        entity.setCategories(categoryEntity);

        //todo: need to replace by email in token + handle case not found
        String email = "lisa@gmail.com";
        UserEntity user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new BadCredentialsException(email));

        entity.setUserEntity(user);
        activityRepository.save(entity);
    }

    @Override
    public List<ActivityDto> getAllActivities(){
        return activityRepository.findAll().stream().map(act -> new ActivityDto(act.getName(), act.getPositive())).toList();
    }
}

//Note: LocalDate.now() => return date
// Instant.now() => return un instant date + time