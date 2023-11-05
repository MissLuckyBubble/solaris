package uni.fmi.Solaris.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.fmi.Solaris.models.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
