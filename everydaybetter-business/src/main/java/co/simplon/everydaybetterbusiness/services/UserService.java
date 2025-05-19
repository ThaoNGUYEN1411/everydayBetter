package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.UserCreate;
import co.simplon.everydaybetterbusiness.entities.User;
import co.simplon.everydaybetterbusiness.models.AuthInfo;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    void create(UserCreate inputs);

    AuthInfo authenticate(UserAuthenticate inputs, HttpServletResponse response);

    User findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String value);

    void logout(HttpServletResponse response);
}
