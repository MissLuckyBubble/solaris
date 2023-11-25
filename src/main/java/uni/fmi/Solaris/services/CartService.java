package uni.fmi.Solaris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.Solaris.models.Cart;
import uni.fmi.Solaris.repo.CartRepo;

@Service
public class CartService extends BaseService<Cart> {
    @Autowired
    private CartRepo cartRepo;

    @Override
    protected JpaRepository<Cart, Long> getRepo() {
        return cartRepo;
    }
}
