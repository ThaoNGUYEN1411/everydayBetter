package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.services.ActivityManagerService;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import co.simplon.everydaybetterbusiness.testUtils.MockMvcSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest extends MockMvcSetup {

    @Autowired
    private ActivityController controller;

    @MockBean
    private ActivityService service;

    @MockBean
    private ActivityManagerService activityManagerService;

    @Override
    protected ActivityController getController(){
        return controller;
    }

    @Test
    void ShouldGetActivityById() throws Exception{
        final var url = "/activities/1";
        mockMvc.perform(get(url)).andDo(print())
                .andExpect(status().isOk());
        verify(activityManagerService).findById(1L);
    }

}