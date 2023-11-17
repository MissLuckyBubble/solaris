package uni.fmi.Solaris.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Product_Category")
public class Category extends MainModel {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(name = "vat_percent", nullable = false)
    private int vatPercent;
    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Category> children;

    public boolean addProduct(final Product product){
        if(products ==null){
            products = new HashSet<>();
        }
        return products.add(product);
    }

    //Tazi klas promenliva ne se zapisva v bazata danni, Transient se izpolzva v java za da ne se serializira
    @Transient
    private String temp;
}
