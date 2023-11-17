package uni.fmi.Solaris.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.Solaris.models.OrderProduct;

public interface OrderProductRepo extends JpaRepository<OrderProduct, Long> {
}
