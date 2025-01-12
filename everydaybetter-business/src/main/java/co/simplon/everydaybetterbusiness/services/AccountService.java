package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.input.AccountAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.input.AccountCreate;
import co.simplon.everydaybetterbusiness.dtos.output.AuthInfo;

public interface AccountService {
    void create(AccountCreate inputs);

    AuthInfo authenticate(AccountAuthenticate inputs);
}
