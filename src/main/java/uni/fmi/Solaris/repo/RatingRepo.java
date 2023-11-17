package uni.fmi.Solaris.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.Solaris.models.Product;
import uni.fmi.Solaris.models.Rating;
import uni.fmi.Solaris.models.User;

import java.util.Collection;
import java.util.List;

public interface RatingRepo extends JpaRepository<Rating,Long> {
    List<Rating> findByUser(final User owner);
    List<Rating> findByProduct(final Product relatedProduct);

    List<Rating> findByProductAndUser(Product product, User user);
}
