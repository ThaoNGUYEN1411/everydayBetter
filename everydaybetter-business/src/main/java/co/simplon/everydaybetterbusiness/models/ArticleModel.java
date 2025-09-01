package co.simplon.everydaybetterbusiness.models;

import java.time.LocalDateTime;

public record ArticleModel(String title, String subTitle, String introduction, String content, String image,
        String thumbnailImage, LocalDateTime publishedDate, String authorName, String categoryName) {
}
