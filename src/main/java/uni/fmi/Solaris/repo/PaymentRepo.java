package uni.fmi.Solaris.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.Solaris.models.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
