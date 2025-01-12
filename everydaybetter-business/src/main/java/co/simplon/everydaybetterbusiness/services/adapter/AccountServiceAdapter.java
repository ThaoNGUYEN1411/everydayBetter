package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.config.JwtProvider;
import co.simplon.everydaybetterbusiness.dtos.input.AccountAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.input.AccountCreate;
import co.simplon.everydaybetterbusiness.dtos.output.AuthInfo;
import co.simplon.everydaybetterbusiness.entities.AccountEntity;
import co.simplon.everydaybetterbusiness.entities.RoleEntity;
import co.simplon.everydaybetterbusiness.repositories.AccountRepository;
import co.simplon.everydaybetterbusiness.repositories.RoleRepository;
import co.simplon.everydaybetterbusiness.services.AccountService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AccountServiceAdapter implements AccountService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepos;


    public AccountServiceAdapter(AccountRepository repository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, RoleRepository roleRepos) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.roleRepos = roleRepos;
    }

    @Override
    public void create(AccountCreate inputs){
        String username = inputs.username();
        String password = passwordEncoder.encode(inputs.password());
        String email = inputs.email();
        Set<RoleEntity> roleDefaultValue = roleRepos.findByRoleDefaultTrue()
                .orElseThrow(() -> new BadCredentialsException(username));
        AccountEntity entity = new AccountEntity(username,email,password, roleDefaultValue);
        repository.save(entity);
    }

    @Override
    public AuthInfo authenticate(AccountAuthenticate inputs){
        String email = inputs.email();
        AccountEntity account = repository.findAllByEmailIgnoreCase(email)
                .orElseThrow(() -> new BadCredentialsException(email));

        List<String> roles = account.getRoles().stream().map(RoleEntity::getRoleName).toList();

        String row = inputs.password();
        String encoded = account.getPassword();
        if (!passwordEncoder.matches(row, encoded)) {
            throw new BadCredentialsException(email);
        }

        String token = jwtProvider.create(email, roles);
        return new AuthInfo(token, roles);
    }
}
