package co.simplon.everydaybetterbusiness.features;

import co.simplon.everydaybetterbusiness.entities.User;
import co.simplon.everydaybetterbusiness.utilsTest.BaseIntegrationTests;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Activities features tests")
public class UserTest extends BaseIntegrationTests {
    private final static String PATH = "/csv/features/users/";

    private final static String USER_BY_EMAIL = """
            select u from User u where u.email = :p1""";

    @DisplayName("Should create a new user")
    @ParameterizedTest
    @CsvFileSource(resources = PATH + "create.csv", numLinesToSkip = 1,
            delimiter = DELIMITER)
    void shouldCreateUser(String json) throws Exception {
        MockHttpServletRequestBuilder builder = requestBuilder("POST", "/users/create", "anonymous", json);
        perform(builder).andExpect(status().isCreated());
        String email = JsonPath.read(json, "$.email");
        User user = findEntityWithParams(User.class, USER_BY_EMAIL, email);
        assertThat(user.getNickname()).isEqualTo(asString(json, "$.nickname"));
        assertThat(user.getEmail()).isEqualTo(asString(json, "$.email"));
    }
}
