package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.input.AccountCreate;
import co.simplon.everydaybetterbusiness.entities.AccountEntity;
import co.simplon.everydaybetterbusiness.repositories.AccountRepository;
import co.simplon.everydaybetterbusiness.services.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceAdapter implements AccountService {
    private final AccountRepository repository;

    public AccountServiceAdapter(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(AccountCreate inputs){
        AccountEntity entity = new AccountEntity();
        entity.setUsername(inputs.username());
        entity.setEmail(inputs.email());
        entity.setPassword(inputs.password());

        repository.save(entity);
    }
}
