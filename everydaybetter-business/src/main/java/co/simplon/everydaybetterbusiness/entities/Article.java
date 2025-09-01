package co.simplon.everydaybetterbusiness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "t_articles")
public class Article extends AbstractEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "sub_title", nullable = false)
    private String subTitle;

    @Column(columnDefinition = "TEXT", name = "introduction", nullable = false)
    private String introduction;

    @Column(columnDefinition = "TEXT", name = "content", nullable = false)
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Article() {
        //ORM
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(final String subTitle) {
        this.subTitle = subTitle;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(final String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(final String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(final LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(final Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof final Article article)) return false;
        if (!super.equals(object)) return false;
        return Objects.equals(subTitle, article.subTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subTitle);
    }

    @Override
    public String toString() {
        return "Article{" +
               "title='" + title + '\'' +
               ", subTitle='" + subTitle + '\'' +
               ", introduction='" + introduction + '\'' +
               ", content='" + content + '\'' +
               ", image='" + image + '\'' +
               ", thumbnailImage='" + thumbnailImage + '\'' +
               ", publishedDate=" + publishedDate +
               ", author=" + author +
               ", category=" + category +
               '}';
    }
}
