package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.input.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.input.UserCreate;
import co.simplon.everydaybetterbusiness.dtos.output.AuthInfo;

public interface UserService {
    void create(UserCreate inputs);

    AuthInfo authenticate(UserAuthenticate inputs);
}
