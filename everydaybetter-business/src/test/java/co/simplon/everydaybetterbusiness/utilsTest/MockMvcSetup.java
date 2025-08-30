package co.simplon.everydaybetterbusiness.utilsTest;

import co.simplon.everydaybetterbusiness.common.utils.AppUtils;
import co.simplon.everydaybetterbusiness.controllers.ControllerAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@AutoConfigureMockMvc
public abstract class MockMvcSetup {

    protected static final ObjectMapper mapper = AppUtils.getMapper();

    protected static final SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor validToken = jwt().jwt(
            Jwt.withTokenValue("tokenValue").claim("sub", "test@example.com").header("header", "header").build()
    );

    @Autowired
    protected MockMvc mockMvc;

    @BeforeAll
    static void initAll() {
        mapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    void init() {
        mockMvc =
                MockMvcBuilders.standaloneSetup(getController()).setControllerAdvice(new ControllerAdvice()).build();
    }

    //This is the abstract method child classes must implement to provide the controller under test.
    protected abstract Object getController();

    protected String asJson(final Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }
}
