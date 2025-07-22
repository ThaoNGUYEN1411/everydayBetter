package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.services.ActivityManagerService;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import co.simplon.everydaybetterbusiness.testUtils.MockMvcSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest extends MockMvcSetup {

    @Autowired
    private ActivityController controller;

    @MockBean
    private ActivityService service;

    @MockBean
    private ActivityManagerService activityManagerService;

    @Override
    protected ActivityController getController() {
        return controller;
    }

//    @Test
//    void ShouldGetActivityById() throws Exception {
//        final var url = "/activities/1";
//        mockMvc.perform(get(url)).andDo(print())
//                .andExpect(status().isOk());
//    }

}
