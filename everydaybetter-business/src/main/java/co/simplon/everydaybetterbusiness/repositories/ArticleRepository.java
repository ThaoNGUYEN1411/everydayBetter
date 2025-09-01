package co.simplon.everydaybetterbusiness.repositories;

import co.simplon.everydaybetterbusiness.entities.Article;
import co.simplon.everydaybetterbusiness.view.ArticleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = """
            select at.id as id,
                   at.title as title,
                   at.subTitle as subTitle,
                   at.introduction as introduction,
                   at.content as content,
                   at.image as image,
                   at.thumbnailImage as thumbnailImage,
                   at.publishedDate as publishedDate,
                   at.author.name as authorName,
                   at.category.name as categoryName
            from Article at
            """)
    List<ArticleView> getAllArticles();
}
