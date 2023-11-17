package uni.fmi.Solaris.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.Solaris.models.Product;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
