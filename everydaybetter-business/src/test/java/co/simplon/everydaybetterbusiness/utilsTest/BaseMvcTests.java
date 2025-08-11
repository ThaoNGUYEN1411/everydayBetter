package co.simplon.everydaybetterbusiness.utilsTest;

import co.simplon.everydaybetterbusiness.EverydaybetterBusinessApplication;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

@SpringBootTest(classes = EverydaybetterBusinessApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class BaseMvcTests {
    protected static final char DELIMITER = '§';

    @Autowired
    private MockMvc mvc;

    @Value("${everydaybetterbusiness.tests.good-token}")
    private String token;

    protected final ResultActions perform(String method,
            String path, String tokenName)
            throws Exception {
        return perform(method, path, tokenName, null);
    }

    protected final ResultActions perform(String method,
            String path, String tokenName, String json)
            throws Exception {
        var builder = requestBuilder(
                method, path,
                tokenName, json);
        return perform(builder);
    }

    protected final MockHttpServletRequestBuilder requestBuilder(
            String method, String path, String tokenName,
            String json) {
        //todo var
        var builder = request(
                HttpMethod.valueOf(method),
                path);
        if (!"anonymous".equals(tokenName)) {
//            builder.header(
//                    "Authorization",
//                    token);
            builder.cookie(new Cookie("jwt", token));
        }
        if (null != json) {
            builder.contentType(MediaType.APPLICATION_JSON)
                    .content(json);
        }
        return builder;
    }

    //.cookie(new Cookie("jwt", token))
    protected final ResultActions perform(
            MockHttpServletRequestBuilder builder)
            throws Exception {
        return mvc.perform(builder);
    }

    protected final MockHttpServletRequestBuilder requestBuilder(
            String method, String path, String tokenName) {
        return requestBuilder(
                method, path, tokenName,
                null);
    }

}
