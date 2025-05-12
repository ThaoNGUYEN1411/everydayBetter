package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.config.JwtProvider;
import co.simplon.everydaybetterbusiness.dtos.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.UserCreate;
import co.simplon.everydaybetterbusiness.models.AuthInfo;
import co.simplon.everydaybetterbusiness.entities.Role;
import co.simplon.everydaybetterbusiness.entities.User;
import co.simplon.everydaybetterbusiness.repositories.RoleRepository;
import co.simplon.everydaybetterbusiness.repositories.UserRepository;
import co.simplon.everydaybetterbusiness.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
    public void create(UserCreate inputs) {
        String nickname = inputs.nickname();
        String password = passwordEncoder.encode(inputs.password());
        String email = inputs.email();
//todo: verify with 401 nickname
        Set<Role> roleDefaultValue = roleRepository.findByRoleDefaultTrue()
                .orElseThrow(() -> new BadCredentialsException(nickname));

        User entity = new User(nickname, email, password, roleDefaultValue);
        userRepository.save(entity);
    }

    @Override
    public AuthInfo authenticate(UserAuthenticate inputs, HttpServletResponse response) {
        String email = inputs.email();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new BadCredentialsException(email));

        List<String> roles = user.getRoles().stream().map(Role::getName).toList();

        String row = inputs.password();
        String encoded = user.getPassword();
        if (!passwordEncoder.matches(row, encoded)) {
            throw new BadCredentialsException(email);
        }

        String token = jwtProvider.create(email, roles);
        System.out.println(token);
        // Set the HTTP-only cookie
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Use secure cookies (only over HTTPS)
        cookie.setPath("/");
        //cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        response.addCookie(cookie);

        return new AuthInfo(user.getNickname(), roles);
    }

    @Override
    public User findByEmailIgnoreCase(final String email) {
        return userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new BadCredentialsException(email));
    }

    @Override
    public Map<String, Object> logout(HttpServletResponse response){
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expire immediately
        response.addCookie(cookie);

        return Map.of("message", "Logged out successfully");
    }

    @Override
    public boolean existsByEmailIgnoreCase(final String email) { //final that you cannot reassign a new value to that parameter within the method body.Without final Without final => but not good!!!
        return userRepository.existsByEmailIgnoreCase(email);
    }
}
/*
Why do we need to add a new cookie instead of updating it in logout?
Cookies are managed by the browser, and once set, they cannot be directly updated or removed from the server. Instead, we can only instruct the browser to overwrite the existing cookie by sending a new cookie with the same name (jwt) but an empty value and an immediate expiration.
 */