package uni.fmi.Solaris.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order extends MainModel{
    @Column(nullable = false, unique = true)
    private String number;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User owner;
    @Enumerated
    private OrderStatus status;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderProduct> orderProducts;
}
