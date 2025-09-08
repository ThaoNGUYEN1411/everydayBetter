package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.models.ArticleModel;

import java.util.List;

public interface ArticleService {
    List<ArticleModel> getAllArticles();

    List<ArticleModel> getAllArticlesByCategoryId(Long id);

    ArticleModel getArticleById(Long id);
}
