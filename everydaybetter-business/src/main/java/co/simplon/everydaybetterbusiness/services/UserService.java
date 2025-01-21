package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.input.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.input.UserCreate;
import co.simplon.everydaybetterbusiness.dtos.output.AuthInfo;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    void create(UserCreate inputs);

    AuthInfo authenticate(UserAuthenticate inputs, HttpServletResponse response);
}
