package uni.fmi.Solaris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.Solaris.models.OrderProduct;
import uni.fmi.Solaris.repo.OrderProductRepo;

@Service
public class OrderProductService extends BaseService<OrderProduct>{
    @Autowired
    private OrderProductRepo orderProductRepo;
    @Override
    protected JpaRepository<OrderProduct, Long> getRepo() {
        return orderProductRepo;
    }
}
