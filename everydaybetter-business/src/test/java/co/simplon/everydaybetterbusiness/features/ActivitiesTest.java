package co.simplon.everydaybetterbusiness.features;

import co.simplon.everydaybetterbusiness.entities.Activity;
import co.simplon.everydaybetterbusiness.utilsTest.BaseIntegrationTests;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Activities features tests")
public class ActivitiesTest extends BaseIntegrationTests {

    private final static String PATH = "/csv/features/activities/";
    private final static String ACTIVITY_BY_NAME_USER_EMAIL = """
            select a from Activity a
                where a.name = :p1
                and a.user.email = :p2
            """;
    private final static String ALL_ACTIVITIES = """
            select a from Activity a where a.user.email = '%s'""";

    @DisplayName("Should create a new activity")
    @ParameterizedTest
    @CsvFileSource(resources = PATH + "create.csv", numLinesToSkip = 1,
            delimiter = DELIMITER)
    void shouldCreateActivity(String json, String email) throws Exception {
        MockHttpServletRequestBuilder builder = requestBuilder("POST", "/activities", "user", json);
        perform(builder).andExpect(status().isCreated());
        String activityName = JsonPath.read(json, "$.name");
        Activity activity = findEntityWithParams(Activity.class, ACTIVITY_BY_NAME_USER_EMAIL, activityName, email);
        assertThat(activity).isNotNull();
        assertThat(activity.getPositive()).isEqualTo(asBoolean(json, "$.positive"));
        assertThat(activity.getDescription()).isEqualTo(asString(json, "$.description"));
        assertThat(activity.getCategory().getId()).isEqualTo(Long.valueOf(asString(json, "$.categoryId")));
    }

    @DisplayName("Should get all activities for an user")
    @ParameterizedTest
    @CsvFileSource(resources = PATH + "email.csv", numLinesToSkip = 1, delimiter = DELIMITER)
    void shouldGetAllActivitiesByUser(String email) throws Exception {
        MockHttpServletRequestBuilder builder = requestBuilder("GET", "/activities", "user");
        perform(builder).andExpect(status().isOk());
        List<Activity> activities = findEntities(Activity.class, ALL_ACTIVITIES, email);
        assertThat(activities).isNotEmpty();
        assertThat(activities.getFirst().getName()).isEqualTo("Lire des livres");
        assertThat(activities.getFirst().getDescription()).isEqualTo("Lire quelques pages d’un livre");
        assertThat(activities.getFirst().getPositive()).isTrue();
        assertThat(activities.getFirst().getCategory().getId()).isEqualTo(3L);
    }

    @DisplayName("Should Update an activity by id")
    @ParameterizedTest
    @CsvFileSource(resources = PATH + "update.csv", numLinesToSkip = 1, delimiter = DELIMITER)
    void shouldUpdate(String json, String email, String id) throws Exception {
        String cleanId = id.trim();
        MockHttpServletRequestBuilder builder = requestBuilder("PUT", "/activities/" + cleanId, "user", json);
        perform(builder).andExpect(status().isOk());
    }

    @DisplayName("Should delete an activity")
    @ParameterizedTest
    @CsvFileSource(resources = PATH + "email.csv", numLinesToSkip = 1, delimiter = DELIMITER)
    void shouldDelete(String email) throws Exception {
        MockHttpServletRequestBuilder builder = requestBuilder("DELETE", "/activities/1", "user");
        perform(builder).andExpect(status().isOk());
        List<Activity> activities = findEntities(Activity.class, ALL_ACTIVITIES, email);
        assertThat(activities).isEmpty();
    }

}
