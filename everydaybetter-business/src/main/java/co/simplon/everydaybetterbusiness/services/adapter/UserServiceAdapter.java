package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.config.JwtProvider;
import co.simplon.everydaybetterbusiness.dtos.input.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.input.UserCreate;
import co.simplon.everydaybetterbusiness.dtos.output.AuthInfo;
import co.simplon.everydaybetterbusiness.entities.RoleEntity;
import co.simplon.everydaybetterbusiness.entities.UserEntity;
import co.simplon.everydaybetterbusiness.repositories.RoleRepository;
import co.simplon.everydaybetterbusiness.repositories.UserRepository;
import co.simplon.everydaybetterbusiness.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceAdapter implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;


    public UserServiceAdapter(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.roleRepository = roleRepository;
    }

    @Override
    public void create(UserCreate inputs){
        String nickname = inputs.nickname();
        String password = passwordEncoder.encode(inputs.password());
        String email = inputs.email();
//todo: verify with 401 nickname
        Set<RoleEntity> roleDefaultValue = roleRepository.findByRoleDefaultTrue()
                .orElseThrow(() -> new BadCredentialsException(nickname));

        UserEntity entity = new UserEntity(nickname,email,password, roleDefaultValue);
        userRepository.save(entity);
    }

    @Override
    public AuthInfo authenticate(UserAuthenticate inputs, HttpServletResponse response){
        String email = inputs.email();
        UserEntity user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new BadCredentialsException(email));

        List<String> roles = user.getRoles().stream().map(RoleEntity::getName).toList();

        String row = inputs.password();
        String encoded = user.getPassword();
        if (!passwordEncoder.matches(row, encoded)) {
            throw new BadCredentialsException(email);
        }

        String token = jwtProvider.create(email, roles);

        // Set the HTTP-only cookie
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Use secure cookies (only over HTTPS)
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        response.addCookie(cookie);

        return new AuthInfo(token, roles);
    }
}
