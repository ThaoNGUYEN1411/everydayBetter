package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.models.ActivityDetailModel;
import co.simplon.everydaybetterbusiness.services.ActivityManagerService;
import co.simplon.everydaybetterbusiness.services.UserActivityTrackingLogService;
import co.simplon.everydaybetterbusiness.utilsTest.MockMvcSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(ActivityController.class)
public class ActivityControllerTest extends MockMvcSetup {
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

//    @Test
//    @WithMockUser(username = "testUser")
//    void shouldCreateActivity() throws Exception {
//        final var url = "/activities";
//        Jwt jwt = Jwt.withTokenValue("fake-token")
//                .header("alg", "none")
//                .claim("sub", "test@example.com")
//                .build();
//        Authentication authentication =
//                new UsernamePasswordAuthenticationToken(jwt, null, List.of());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        ActivityCreate activityCreate = new ActivityCreate("faire du sport", "faire du sport chaque jour", true, "1");
//
//        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .content(asJson(activityCreate)))
//                .andDo(print())
//                .andExpect(status().isCreated());
    //verify(service).create(any(UserCreate.class));
//    }

    // Simule un utilisateur connecté
    @Test
    @WithMockUser(username = "testUser")
    void ShouldGetActivityById() throws Exception {
        final var url = "/activities/1";
        //give token to test
        Jwt jwt = Jwt.withTokenValue("fake-token")
                .header("alg", "none")
                .claim("sub", "test@example.com")
                .build();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(jwt, null, List.of());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ActivityDetailModel activity = new ActivityDetailModel(1L, "faire du sport", "description", true, new ActivityDetailModel.Category(1L, "Santé"));

        when(activityManagerService.findById(eq(1L), eq("test@example.com")))
                .thenReturn(activity);

        mockMvc.perform(get(url)).andDo(print())
                .andExpect(status().isOk());
    }
}
