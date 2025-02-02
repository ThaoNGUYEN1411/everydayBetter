package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.common.Utils;
import co.simplon.everydaybetterbusiness.dtos.input.ActivityCreate;
import co.simplon.everydaybetterbusiness.dtos.input.ActivityUpdate;
import co.simplon.everydaybetterbusiness.dtos.output.ActivityDto;
import co.simplon.everydaybetterbusiness.entities.ActivityEntity;
import co.simplon.everydaybetterbusiness.entities.CategoryEntity;
import co.simplon.everydaybetterbusiness.entities.UserEntity;
import co.simplon.everydaybetterbusiness.exceptions.NotFoundException;
import co.simplon.everydaybetterbusiness.models.ActivityModel;
import co.simplon.everydaybetterbusiness.repositories.ActivityRepository;
import co.simplon.everydaybetterbusiness.repositories.CategoryRepository;
import co.simplon.everydaybetterbusiness.repositories.UserRepository;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ActivityServiceAdapter implements ActivityService {
    private final ActivityRepository activityRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final Utils utils;

    public ActivityServiceAdapter(ActivityRepository activityRepository, CategoryRepository categoryRepository, UserRepository userRepository, Utils utils) {
        this.activityRepository = activityRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.utils = utils;
    }

    @Override
    public void create(final ActivityCreate inputs){
        ActivityEntity entity = new ActivityEntity();

        entity.setName(inputs.name());
        entity.setDescription(inputs.description());
        entity.setPositive(inputs.positive());

        String categoryIds = inputs.categoryIds();
        if (categoryIds != null){
            Set<CategoryEntity> categoryEntities = new HashSet<>(categoryRepository.findAllById(Collections.singleton(Long.parseLong(categoryIds))));
            entity.setCategories(categoryEntities);
        }else {
            entity.setCategories(null);
        }

        //todo: need to replace by email in token + handle case not found
        String email = utils.getAuthenticatedUser();
        UserEntity user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new BadCredentialsException(email));

        entity.setUserEntity(user);
        activityRepository.save(entity);
    }

    @Override
    public List<ActivityDto> getAllActivitiesByUser(){
        String email = utils.getAuthenticatedUser();
        UserEntity user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new BadCredentialsException(email));
        List<ActivityEntity> activities = activityRepository.findByUserId(user.getId()).orElseThrow(()-> new NotFoundException("Activity with ID not found"));

        return activities.stream().map(act -> new ActivityDto(act.getId(), act.getName(), act.getPositive())).toList();
    }

    @Override
    public ActivityModel findById(final Long id){
        ActivityEntity entity = activityRepository.findById(id).orElseThrow(() -> new NotFoundException("Activity with ID " + id + " not found"));
        return new ActivityModel(entity.getId(), entity.getName(), entity.getDescription(), entity.getPositive(),
                entity.getCategories().stream().map(c -> new ActivityModel.Category(c.getId(), c.getName())).toList());
    }

    @Override
    public void delete(final Long id){
        activityRepository.deleteById(id);
    }

    @Override
    public void update(Long id, @Valid ActivityUpdate inputs){
        ActivityEntity entity = activityRepository.findById(id).orElseThrow(()-> new NotFoundException("Activity with ID " + id + " not found"));

        entity.setName(inputs.name());
        entity.setDescription(inputs.description());
        entity.setPositive(inputs.positive());

        List<Long> categoryIds = inputs.categoryIds();
        Set<CategoryEntity> categoryEntities = new HashSet<>(categoryRepository.findAllById(categoryIds));
        entity.setCategories(categoryEntities);

        activityRepository.save(entity);
    }

}

//Note: LocalDate.now() => return date
// Instant.now() => return un instant date + time