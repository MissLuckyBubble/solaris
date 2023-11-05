package uni.fmi.Solaris.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(length = 50, nullable = false, unique = true)
    private String name;
    @Column(name= "vat_percent", nullable = false)
    private int vatPercent;

    //Tazi klas promenliva ne se zapisva v bazata danni, Transient se izpolzva v java za da ne se serializira
    @Transient
    private String temp;
}
