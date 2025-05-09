package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.UserCreate;
import co.simplon.everydaybetterbusiness.services.UserService;
import co.simplon.everydaybetterbusiness.testUtils.MockMvcSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc(addFilters = false) // désactive les filtres Spring Security
@WebMvcTest(UserController.class)
class UserControllerTest extends MockMvcSetup {

    @Autowired
    private UserController controller;

    @MockBean
    private UserService service;
    @BeforeEach
    void setup() {
        when(service.existsByEmailIgnoreCase(anyString())).thenReturn(false);
    }

    @Override
    protected UserController getController(){
        return controller;
    }
//test not work with @UniqueEmail
    @Test
    void shouldCreateUser() throws Exception{
        final var url ="/users/create";
        when(service.existsByEmailIgnoreCase("test@gmail.com")).thenReturn(false);
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJson(new UserCreate("Thao", "test@gmail.com", "Password123!"))))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(service).create(any(UserCreate.class));
    }

    @Test
    void shouldAuthenticate() throws Exception{
        final var url = "/users/authenticate";
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)

                .content(asJson(new UserAuthenticate("test@gmail.com", "Password123!"))))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(service).authenticate(any(UserAuthenticate.class), any());
    }

    @Test
    void shouldLogout() throws Exception{
        final var url = "/users/logout";
        mockMvc.perform(post(url)
                        .header("Authorization", "test")) // si sécurité active
                .andDo(print())
                .andExpect(status().isCreated());
    }
}