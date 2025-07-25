package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.config.JwtProvider;
import co.simplon.everydaybetterbusiness.dtos.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.UserCreate;
import co.simplon.everydaybetterbusiness.entities.Role;
import co.simplon.everydaybetterbusiness.entities.User;
import co.simplon.everydaybetterbusiness.exceptions.ResourceNotFoundException;
import co.simplon.everydaybetterbusiness.models.AuthInfo;
import co.simplon.everydaybetterbusiness.repositories.UserRepository;
import co.simplon.everydaybetterbusiness.services.RoleService;
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
    private final RoleService roleService;

    public UserServiceAdapter(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.roleService = roleService;
    }

    @Override
    public void create(final UserCreate inputs) {
        final String nickname = inputs.nickname();
        final String password = passwordEncoder.encode(inputs.password());
        final String email = inputs.email();
        final Set<Role> defaultRoles = roleService.findByRoleDefaultTrue();
        if (!defaultRoles.isEmpty()) {
            final User entity = new User(nickname, email, password, defaultRoles);
            userRepository.save(entity);
        } else {
            throw new ResourceNotFoundException("Role default not found");
        }
    }

    @Override
    public AuthInfo authenticate(final UserAuthenticate inputs, final HttpServletResponse response) {
        final String email = inputs.email();
        final User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new BadCredentialsException(email));

        final List<String> roles = user.getRoles().stream().map(Role::getName).toList();

        final String row = inputs.password();
        final String encoded = user.getPassword();
        if (!passwordEncoder.matches(row, encoded)) {
            throw new BadCredentialsException(email);
        }

        String token = jwtProvider.create(email, roles);
        // add token in cookie
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // Use secure cookies
        cookie.setPath("/");
        response.addCookie(cookie);
        return new AuthInfo(user.getNickname(), roles);
    }

    @Override
    public User findByEmailIgnoreCase(final String email) {
        return userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new BadCredentialsException(email));
    }

    @Override
    public boolean existsByEmailIgnoreCase(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public void logout(final HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(false); //false: testing on HTTP => when deployment it's true
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expire immediately
        response.addCookie(cookie);
    }
}
/*
Why do we need to add a new cookie instead of updating it in log out?
Cookies are managed by the browser, and once set, they cannot be directly updated or removed from the server. Instead, we can only instruct the browser to overwrite the existing cookie by sending a new cookie with the same name (jwt) but an empty value and an immediate expiration.
final that you cannot reassign a new value to that parameter within the method body.Without final => but not good!!!
*/
