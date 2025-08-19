package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.ActivityCreate;
import co.simplon.everydaybetterbusiness.dtos.ActivityUpdate;
import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.entities.Category;
import co.simplon.everydaybetterbusiness.entities.User;
import co.simplon.everydaybetterbusiness.mappers.ActivityMapper;
import co.simplon.everydaybetterbusiness.models.ActivityDetailModel;
import co.simplon.everydaybetterbusiness.models.ActivityModel;
import co.simplon.everydaybetterbusiness.services.ActivityManagerService;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import co.simplon.everydaybetterbusiness.services.CategoryService;
import co.simplon.everydaybetterbusiness.services.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ActivityManagerServiceAdapter implements ActivityManagerService {
    private final ActivityService activityService;
    private final UserService userService;
    private final CategoryService categoryService;

    public ActivityManagerServiceAdapter(ActivityService activityService, UserService userService, CategoryService categoryService) {
        this.activityService = activityService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public ActivityModel create(final ActivityCreate inputs, final String email) {
        Activity entity = new Activity();
        entity.setName(inputs.name());
        entity.setDescription(inputs.description());
        entity.setPositive(inputs.positive());
        final User user = userService.findByEmailIgnoreCase(email);
        entity.setUser(user);
        final Long id = Long.valueOf(inputs.categoryId());
        final Category category = categoryService.findById(id);
        entity.setCategory(category);
        return ActivityMapper.toModel(activityService.save(entity));
    }

    @Override
    public List<ActivityModel> getAllActivitiesByUser(final String email) {
        final User user = userService.findByEmailIgnoreCase(email);

        List<Activity> activities = activityService.findByUserId(user.getId());
        return activities.stream().map(act -> new ActivityModel(act.getId(), act.getName(), act.getPositive())).toList();
    }

    @Override
    public ActivityDetailModel findById(final Long id, final String email) {
        Activity entity = activityService.findById(id);
        if (!entity.getUser().getEmail().equals(email)) {
            throw new BadCredentialsException(email);
        }
        return new ActivityDetailModel(
                entity.getId(), entity.getName(), entity.getDescription(), entity.getPositive(),
                new ActivityDetailModel.Category(entity.getCategory().getId(), entity.getCategory().getName()));
    }

    @Override
    @Transactional
    public void update(final Long id, final ActivityUpdate inputs, final String email) {
        Activity entity = activityService.findById(id);

        final User user = userService.findByEmailIgnoreCase(email);
        if (Objects.equals(user.getId(), entity.getUser().getId())) {
            entity.setName(inputs.name());
            entity.setDescription(inputs.description());
            entity.setPositive(inputs.positive());
            Long categoryId = Long.valueOf(inputs.categoryId());
            Category category = categoryService.findById(categoryId);
            entity.setCategory(category);
            activityService.save(entity);

        } else {
            throw new BadCredentialsException(email);
        }

    }
}
