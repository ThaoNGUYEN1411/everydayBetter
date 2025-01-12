package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.input.HabitCreate;
import co.simplon.everydaybetterbusiness.entities.AccountEntity;
import co.simplon.everydaybetterbusiness.entities.HabitEntity;
import co.simplon.everydaybetterbusiness.entities.LabelEntity;
import co.simplon.everydaybetterbusiness.repositories.AccountRepository;
import co.simplon.everydaybetterbusiness.repositories.HabitRepository;
import co.simplon.everydaybetterbusiness.repositories.LabelRepository;
import co.simplon.everydaybetterbusiness.services.HabitService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class HabitServiceAdapter implements HabitService {
    private final HabitRepository habitRepository;
    private final LabelRepository labelRepository;
    private final AccountRepository accountRepository;
    public HabitServiceAdapter(HabitRepository habitRepository, LabelRepository labelRepository, AccountRepository accountRepository) {
        this.habitRepository = habitRepository;
        this.labelRepository = labelRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void create(final HabitCreate inputs){
        HabitEntity entity = new HabitEntity();

        entity.setHabitName(inputs.habitName());
        entity.setDescription(inputs.description());
        entity.setPositive(inputs.positive());
        entity.setCreatedTime(Instant.now());
        entity.setUpdateTime(Instant.now());

        String labelName = inputs.labelName();
//Refactor with Optional
        LabelEntity labelEntity = labelRepository.findByLabelNameIgnoreCase(labelName);
        if (labelEntity == null){
            labelEntity = new LabelEntity();
            labelEntity.setLabelName(labelName);
            labelRepository.save(labelEntity);
        }
        entity.setLabelEntity(labelEntity);
        //todo: need to replace by username in token + handle case not found
        String username = "thao";
        AccountEntity account = accountRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new BadCredentialsException(username));
        entity.setAccountEntity(account);
        habitRepository.save(entity);

    }
}

//Note: LocalDate.now() => return date
// Instant.now() => return un instant date + time