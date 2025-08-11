package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.UserAuthenticate;
import co.simplon.everydaybetterbusiness.services.UserService;
import co.simplon.everydaybetterbusiness.utilsTest.MockMvcSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest extends MockMvcSetup {
    @Autowired
    private UserController controller;

    @MockitoBean
    private UserService service;

    @Override
    protected UserController getController() {
        return controller;
    }

    @Test
    void shouldAuthenticate() throws Exception {
        final var url = "/users/authenticate";
        mockMvc.perform(post(url)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(asJson(new UserAuthenticate("email@exemple.com", "Secret778!"))))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(service).authenticate(any(UserAuthenticate.class), any());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/user-authenticate-validation-data.csv", numLinesToSkip = 1)
    void shouldNotAuthenticateWhenUserAuthenticateInvalid(String json) throws Exception {
        final var url = "/users/authenticate";
        mockMvc.perform(post(url)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldLogout() throws Exception {
        final var url = "/users/logout";
        mockMvc.perform(post(url))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
