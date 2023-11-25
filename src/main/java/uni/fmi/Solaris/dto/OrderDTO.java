package uni.fmi.Solaris.dto;

import lombok.Getter;
import lombok.Setter;
import uni.fmi.Solaris.models.OrderStatus;
import uni.fmi.Solaris.models.User;

@Getter
@Setter
public class OrderDTO extends BaseDTO {
    private String number;
    private User owner;
    private OrderStatus status;
}
