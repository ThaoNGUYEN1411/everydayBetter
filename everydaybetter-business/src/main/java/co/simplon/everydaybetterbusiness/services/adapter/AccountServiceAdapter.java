package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.input.AccountCreate;
import co.simplon.everydaybetterbusiness.entities.AccountEntity;
import co.simplon.everydaybetterbusiness.repositories.AccountRepository;
import co.simplon.everydaybetterbusiness.services.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceAdapter implements AccountService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceAdapter(AccountRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(AccountCreate inputs){
        AccountEntity entity = new AccountEntity();
        entity.setUsername(inputs.username());
        entity.setEmail(inputs.email());
        String passwordRow = inputs.password();
        String passwordEncode = passwordEncoder.encode(passwordRow);

        entity.setPassword(passwordEncode);

        repository.save(entity);
    }
}
