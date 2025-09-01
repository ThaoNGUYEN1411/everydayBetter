package co.simplon.everydaybetterbusiness.features;

import co.simplon.everydaybetterbusiness.entities.Article;
import co.simplon.everydaybetterbusiness.utilsTest.BaseIntegrationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Articles features tests")
class ArticleTest extends BaseIntegrationTests {
    private static final String ALL_ARTICLE = """
            select a from Article a""";

    @DisplayName("Should get all articles")
    @Test
    void shouldGetAllArticles() throws Exception {
        MockHttpServletRequestBuilder builder = requestBuilder("GET", "/articles", "anonymous");
        perform(builder).andExpect(status().isOk());
        List<Article> articles = findEntities(Article.class, ALL_ARTICLE);
        assertThat(articles).isNotEmpty();
    }
}
