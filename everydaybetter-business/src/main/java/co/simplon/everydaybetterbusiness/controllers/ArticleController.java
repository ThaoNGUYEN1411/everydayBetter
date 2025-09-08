package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.models.ArticleModel;
import co.simplon.everydaybetterbusiness.services.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(final ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all articles", description = "Get all articles order by published date")
    public ResponseEntity<List<ArticleModel>> getAllArticles() {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticles());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all articles by category id", description = "Get all articles by category id order by published date")
    public ResponseEntity<List<ArticleModel>> getAllArticlesByCategory(@PathVariable final Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticlesByCategoryId(id));
    }

    @GetMapping(value = "detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "get an article by id", description = "get an activity by id")
    public ResponseEntity<ArticleModel> getArticleById(@PathVariable final Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getArticleById(id));
    }
}
