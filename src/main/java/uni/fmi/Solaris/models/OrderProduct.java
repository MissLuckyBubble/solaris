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
@Table(name = "ORDER_PRODUCTS")
public class OrderProduct extends MainModel {
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int vat;
    @Column(nullable = false, name = "PRICE_POT_VAT")
    private BigDecimal priceNotVat;

}
