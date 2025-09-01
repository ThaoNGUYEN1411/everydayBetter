package co.simplon.everydaybetterbusiness.view;

import java.time.LocalDateTime;

public interface ArticleView {
    Long getId();

    String getTitle();

    String getSubTitle();

    String getIntroduction();

    String getContent();

    String getImage();

    String getThumbnailImage();

    LocalDateTime getPublishedDate();

    String getAuthorName();

    String getCategoryName();
}
