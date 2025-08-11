package co.simplon.everydaybetterbusiness.utilsTest;

import co.simplon.everydaybetterbusiness.common.utils.ConvertirUtils;
import co.simplon.everydaybetterbusiness.controllers.ControllerAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

@ActiveProfiles("test") //Todo: check avec prod
public abstract class MockMvcSetup {

    protected static final ObjectMapper mapper = ConvertirUtils.getMapper();
    protected MockMvc mockMvc;

    //Registers a module to handle Java 8+ date/time types (like LocalDate) for JSON (de)serialization.
    // gérer correctement les types de date/temps de Java 8+ comme : LocalDate, LocalDateTime
    //un ObjectMapper customisé, tu dois conserver :
    @BeforeAll
    static void initAll() {
        mapper.registerModule(new JavaTimeModule());
    }

    //A reusable JSON mapper for converting Java objects to/from JSON.
    //ConvertirUtils.getMapper() is a custom method returning a configured ObjectMapper.
    protected String asJson(final Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    /*
    standaloneSetup(...): Initializes MockMvc with only the specified controller — very fast and lightweight.

    getController(): Abstract method; each subclass provides its specific controller instance.

    .setControllerAdvice(...): Adds a global exception handler (@ControllerAdvice) for the test.
     */
    @BeforeEach
    void init() {
        mockMvc =
                MockMvcBuilders.standaloneSetup(getController()).setControllerAdvice(new ControllerAdvice()).build();
    }

    //This is the abstract method child classes must implement to provide the controller under test.
    protected abstract Object getController();
}
/*
You use @ActiveProfiles("test") when you want your test classes to:

Load different application settings (e.g., application-test.yml or application-test.properties)

Use mocked beans, in-memory databases, or test-specific configurations
Without it:
If you don’t use @ActiveProfiles("test"), Spring Boot will load the default profile (application.yml or application-template.properties), which is often meant for production or development — not ideal for tests.

 This is an abstract class — you can't instantiate it directly.
Child test classes extend it and implement the getController() method to provide their specific controller to test.

Defines the MockMvc object used to simulate HTTP requests/responses in tests.
 */
//MockMvc: Pour simuler des requêtes HTTP sans serveur
//standaloneSetup()	Pour des tests unitaires rapides et isolés, pas besoin de charger toute l'application Spring
//Tests ciblés : tu testes juste le contrôleur, pas les services ni les beans externes.
