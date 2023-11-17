package uni.fmi.Solaris.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PAYMENTS")
public class Payment extends MainModel {
    @Column(length = 36, nullable = false, unique = true)
    private String token;
    private boolean successful = false;
    private BigDecimal sum;
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;


}

