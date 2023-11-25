package uni.fmi.Solaris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.Solaris.models.CartProduct;
import uni.fmi.Solaris.repo.CartProductRepo;

@Service
public class CartProductService extends BaseService<CartProduct> {
    @Autowired
    private CartProductRepo cartProductRepo;

    @Override
    protected JpaRepository<CartProduct, Long> getRepo() {
        return cartProductRepo;
    }
}
