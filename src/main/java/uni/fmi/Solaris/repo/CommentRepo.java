package uni.fmi.Solaris.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.Solaris.models.Comment;

public interface CommentRepo extends JpaRepository<Comment,Long> {
}
