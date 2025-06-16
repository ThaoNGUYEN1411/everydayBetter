package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.common.AppUtils;
import co.simplon.everydaybetterbusiness.dtos.ActivityCreate;
import co.simplon.everydaybetterbusiness.dtos.ActivityUpdate;
import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.entities.Category;
import co.simplon.everydaybetterbusiness.entities.User;
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
    public void create(final ActivityCreate inputs) {
        Activity entity = new Activity();
        entity.setName(inputs.name());
        entity.setDescription(inputs.description());
        entity.setPositive(inputs.positive());
        final String email = AppUtils.getAuthenticatedUser();
        final User user = userService.findByEmailIgnoreCase(email);
        entity.setUser(user);

        final Long id = Long.valueOf(inputs.categoryId());
        final Category category = categoryService.findById(id);
        entity.setCategory(category);

        activityService.save(entity);
    }

    @Override
    public List<ActivityModel> getAllActivitiesByUser() {
        final String email = AppUtils.getAuthenticatedUser();
        final User user = userService.findByEmailIgnoreCase(email);

        List<Activity> activities = activityService.findByUserId(user.getId());
        return activities.stream().map(act -> new ActivityModel(act.getId(), act.getName(), act.getPositive())).toList();
    }

    @Override
    public ActivityDetailModel findById(Long id) {
        Activity entity = activityService.findById(id);
        final String email = AppUtils.getAuthenticatedUser();
        if (!entity.getUser().getEmail().equals(email)){
            throw new BadCredentialsException(email);
        }
        return new ActivityDetailModel(entity.getId(), entity.getName(), entity.getDescription(), entity.getPositive(),
                new ActivityDetailModel.Category(entity.getCategory().getId(), entity.getCategory().getName()));
    }

    @Override
    @Transactional
    public void update(Long id, ActivityUpdate inputs) {
        Activity entity = activityService.findById(id);

        final String email = AppUtils.getAuthenticatedUser();
        final User user = userService.findByEmailIgnoreCase(email);
        if (Objects.equals(user.getId(), entity.getUser().getId())){
            entity.setName(inputs.name());
            entity.setDescription(inputs.description());
            entity.setPositive(inputs.positive());
            Long categoryId = Long.valueOf(inputs.categoryId());
            Category category = categoryService.findById(categoryId);
            entity.setCategory(category);
            activityService.save(entity);
            System.out.println("save");

        }else {
            throw new BadCredentialsException(email);
        }

    }
}
