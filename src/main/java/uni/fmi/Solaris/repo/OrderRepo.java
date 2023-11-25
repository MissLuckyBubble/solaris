package uni.fmi.Solaris.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.Solaris.models.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
