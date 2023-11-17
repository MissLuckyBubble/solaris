package uni.fmi.Solaris.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.Solaris.models.CartProduct;

public interface CartProductRepo extends JpaRepository<CartProduct, Long> {
}
