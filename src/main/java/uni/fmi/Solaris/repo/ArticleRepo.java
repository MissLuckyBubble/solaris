package uni.fmi.Solaris.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.Solaris.models.Article;

public interface ArticleRepo extends JpaRepository<Article, Long> {
}
