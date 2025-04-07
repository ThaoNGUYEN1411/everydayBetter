package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.UserCreate;
import co.simplon.everydaybetterbusiness.dtos.output.AuthInfo;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    void create(UserCreate inputs);

    AuthInfo authenticate(UserAuthenticate inputs, HttpServletResponse response);

//    void logout(HttpServletResponse response);
}
