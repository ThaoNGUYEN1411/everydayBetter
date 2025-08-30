package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.services.ActivityManagerService;
import co.simplon.everydaybetterbusiness.services.UserActivityTrackingLogService;
import co.simplon.everydaybetterbusiness.utilsTest.MockMvcSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest extends MockMvcSetup {
    @Autowired
    private ActivityController controller;

    @MockitoBean
    private ActivityManagerService activityManagerService;

    @MockitoBean
    private UserActivityTrackingLogService userActivityTrackingLogService;

    @Override
    protected ActivityController getController() {
        return controller;
    }

    @Test
    void ShouldGetActivityById() throws Exception {
        final var url = "/activities/1";
        mockMvc.perform(get(url).with(validToken)).andDo(print()).andExpect(status().isOk());
        verify(activityManagerService).findById(1L, "test@example.com");
    }
}
