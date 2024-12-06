package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.input.HabitCreate;
import co.simplon.everydaybetterbusiness.entities.HabitEntity;
import co.simplon.everydaybetterbusiness.repositories.HabitRepository;
import co.simplon.everydaybetterbusiness.services.HabitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HabitServiceAdapter implements HabitService {
    private final HabitRepository repos;

    @Override
    public void create(final HabitCreate inputs){
        HabitEntity entity = new HabitEntity();
        entity.setHabitName(inputs.habitName());
        entity.setDescription(inputs.description());
        entity.setIsPositive(inputs.isPositive());
        entity.setResetCounter(inputs.resetCounter());
        entity.setLabelName(inputs.labelName());

        repos.save(entity);

    }
}
