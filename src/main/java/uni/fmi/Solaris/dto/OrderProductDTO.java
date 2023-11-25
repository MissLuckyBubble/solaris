package uni.fmi.Solaris.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderProductDTO extends BaseDTO {
    private long orderId;
    private long productId;
    private int quantity;
    private int vat;
    private BigDecimal priceNotVat;
}
