package co.simplon.everydaybetterbusiness.features;

import co.simplon.everydaybetterbusiness.entities.Article;
import co.simplon.everydaybetterbusiness.utilsTest.BaseIntegrationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Articles features tests")
class ArticleTest extends BaseIntegrationTests {
    private static final String ALL_ARTICLE = """
            select a from Article a""";

    private static final String ALL_ARTICLE_BY_CATEGORY_ID = """
            select a from Article a where a.category.id = %s
            """;

    @DisplayName("Should get all articles order desc by published date")
    @Test
    void shouldGetAllArticles() throws Exception {
        MockHttpServletRequestBuilder builder = requestBuilder("GET", "/articles", "anonymous");
        perform(builder).andExpect(status().isOk());
        List<Article> articles = findEntities(Article.class, ALL_ARTICLE);
        assertThat(articles).isNotEmpty();
        assertEquals(2, articles.size());
    }

    @DisplayName("Should get all articles by category id and order desc by published date")
    @Test
    void shouldGetAllArticlesByCategory() throws Exception {
        MockHttpServletRequestBuilder builder = requestBuilder("GET", "/articles/1", "anonymous");
        perform(builder).andExpect(status().isOk());
        List<Article> articles = findEntities(Article.class, ALL_ARTICLE_BY_CATEGORY_ID, 2L);
        assertEquals(1, articles.size());
    }

    @DisplayName("Should get all articles by category id and order desc by published date")
    @Test
    void shouldGetAllArticlesByCategoryIsEmpty() throws Exception {
        MockHttpServletRequestBuilder builder = requestBuilder("GET", "/articles/1", "anonymous");
        perform(builder).andExpect(status().isOk());
        List<Article> articles = findEntities(Article.class, ALL_ARTICLE_BY_CATEGORY_ID, 1L);
        assertThat(articles).isEmpty();
    }
}
