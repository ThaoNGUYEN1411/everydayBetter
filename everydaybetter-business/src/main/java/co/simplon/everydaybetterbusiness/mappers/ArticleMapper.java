package co.simplon.everydaybetterbusiness.mappers;

import co.simplon.everydaybetterbusiness.models.ArticleModel;
import co.simplon.everydaybetterbusiness.view.ArticleView;

public final class ArticleMapper {
    public static ArticleModel toModel(ArticleView articleView) {
        return new ArticleModel(
                articleView.getId(),
                articleView.getTitle(),
                articleView.getSubTitle(),
                articleView.getIntroduction(),
                articleView.getContent(),
                articleView.getImage(),
                articleView.getThumbnailImage(),
                articleView.getPublishedDate(),
                articleView.getAuthorName(),
                articleView.getCategoryName());
    }
}
