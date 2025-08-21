package co.simplon.everydaybetterbusiness.features;

import co.simplon.everydaybetterbusiness.entities.TrackingLog;
import co.simplon.everydaybetterbusiness.utilsTest.BaseIntegrationTests;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Tracking log tests")
public class TrackingLogTest extends BaseIntegrationTests {
    private final static String PATH = "/csv/features/trackinglogs/";
    private final static String TRACKING_LOG_BY_ACTIVITY_ID_AND_TRACKEDDATE = """
            select t from TrackingLog t
            Where t.activity.id = :p1
            and t.trackedDate = :p2""";

    private final static String ALL_TRACKING_LOGS = """
            select t from TrackingLog t
            where t.activity.id = :activityId
            and t.trackedDate >= :startDate
            and t.trackedDate <= :endDate
            order by t.trackedDate
            """;

    private final static String ALL_TRACKING_LOGS_WITH_ACTIVITY_ID = """
            select t from TrackingLog t
            where t.activity.id = '%s'
            """;

    @DisplayName("Should create a new tracking log")
    @ParameterizedTest
    @CsvFileSource(resources = PATH + "create.csv", numLinesToSkip = 1,
            delimiter = DELIMITER)
    void shouldCreateTrackingLog(String json) throws Exception {
        MockHttpServletRequestBuilder builder = requestBuilder("POST", "/tracking-logs/", "user", json);
        perform(builder).andExpect(status().isCreated());
        Long activityId = Long.valueOf(JsonPath.read(json, "$.activityId").toString());
        LocalDate trackedDate = LocalDate.parse(JsonPath.read(json, "$.trackedDate").toString());

        TrackingLog trackingLog = findEntityWithParams(
                TrackingLog.class,
                TRACKING_LOG_BY_ACTIVITY_ID_AND_TRACKEDDATE,
                activityId, trackedDate
        );
        assertThat(trackingLog).isNotNull();
        assertThat(trackingLog.getDone()).isTrue();
    }

    @DisplayName("Should Get all activities with tracking log")
    @Test
    void shouldGetAllActivityTrackingLog() throws Exception {
        LocalDate startDate = LocalDate.of(2025, 6, 1);
        LocalDate endDate = LocalDate.of(2025, 6, 29);
        MockHttpServletRequestBuilder builder = requestBuilder("GET", "/tracking-logs/", "user")
                .param("start-date", String.valueOf(startDate))
                .param("end-date", String.valueOf(endDate));
        perform(builder).andExpect(status().isOk());
    }

    @DisplayName("Should update a tracking log")
    @ParameterizedTest
    @CsvFileSource(resources = PATH + "update.csv", numLinesToSkip = 1,
            delimiter = DELIMITER)
    void shouldUpdateTrackingActivity(String json) throws Exception {
        MockHttpServletRequestBuilder builder = requestBuilder("PATCH", "/tracking-logs/update", "user", json);
        perform(builder).andExpect(status().isOk());

        Long activityId = Long.valueOf(JsonPath.read(json, "$.activityId").toString());
        LocalDate trackedDate = LocalDate.parse(JsonPath.read(json, "$.trackedDate").toString());

        TrackingLog trackingLog = findEntityWithParams(
                TrackingLog.class,
                TRACKING_LOG_BY_ACTIVITY_ID_AND_TRACKEDDATE,
                activityId, trackedDate
        );
        assertThat(trackingLog).isNotNull();
        assertThat(trackingLog.getDone()).isFalse();
    }

    @DisplayName("Should delete a tracking log")
    @Test
    void shouldDeleteTrackingActivity() throws Exception {
//        final String email = "lucia@gmail.com";
        MockHttpServletRequestBuilder builder = requestBuilder("DELETE", "/activities/1", "user");
        perform(builder).andExpect(status().isOk());
//        List<TrackingLog> trackingLogs = findEntitiesWithParams(TrackingLog.class, ALL_TRACKING_LOGS_WITH_ACTIVITY_ID, email, 1);
//        assertThat(trackingLogs).isEmpty();
    }

    @DisplayName("Should Retry Activities progress analytics")
    @Test
    void shouldGetActivitiesProgressAnalytics() throws Exception {
        LocalDate startDate = LocalDate.of(2025, 6, 1);
        LocalDate endDate = LocalDate.of(2025, 6, 29);
        MockHttpServletRequestBuilder builder = requestBuilder("GET", "/tracking-logs/progress-summary", "user")
                .param("start-date", String.valueOf(startDate))
                .param("end-date", String.valueOf(endDate));
        perform(builder).andExpect(status().isOk());
    }
}
