package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.mappers.ArticleMapper;
import co.simplon.everydaybetterbusiness.models.ArticleModel;
import co.simplon.everydaybetterbusiness.repositories.ArticleRepository;
import co.simplon.everydaybetterbusiness.services.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceAdapter implements ArticleService {
    private ArticleRepository articleRepository;

    public ArticleServiceAdapter(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleModel> getAllArticles() {
        return articleRepository.getAllArticles().stream().map(ArticleMapper::toModel).toList();
    }

    @Override
    public List<ArticleModel> getAllArticlesByCategoryId(final Long id) {
        return articleRepository.getAllArticlesByCategoryId(id).stream().map(ArticleMapper::toModel).toList();
    }

    @Override
    public ArticleModel getArticleById(final Long id) {
        return ArticleMapper.toModel(articleRepository.getArticleById(id));
    }
}
