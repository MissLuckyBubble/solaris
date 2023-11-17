package uni.fmi.Solaris.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CARTS")
public class Cart extends MainModel {
    @OneToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

}
