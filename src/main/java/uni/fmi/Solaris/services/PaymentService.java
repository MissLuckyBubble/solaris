package uni.fmi.Solaris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.Solaris.models.Payment;
import uni.fmi.Solaris.repo.PaymentRepo;

@Service
public class PaymentService extends BaseService<Payment> {
    @Autowired
    private PaymentRepo paymentRepo;

    @Override
    protected JpaRepository<Payment, Long> getRepo() {
        return paymentRepo;
    }
}
