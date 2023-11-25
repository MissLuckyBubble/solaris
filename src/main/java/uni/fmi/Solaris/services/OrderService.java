package uni.fmi.Solaris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.Solaris.models.Order;
import uni.fmi.Solaris.repo.OrderRepo;

@Service
public class OrderService extends BaseService<Order> {
    @Autowired
    private OrderRepo orderRepo;

    @Override
    protected JpaRepository<Order, Long> getRepo() {
        return orderRepo;
    }
}
