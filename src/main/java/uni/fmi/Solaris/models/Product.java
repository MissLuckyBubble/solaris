package uni.fmi.Solaris.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product extends MainModel{
    @Column(length = 256, name = "NAME", nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = true)
    private String description;
    private int quantity;
    @Transient
    private String temp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
