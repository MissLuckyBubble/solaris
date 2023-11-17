package uni.fmi.Solaris.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uni.fmi.Solaris.models.Category;
import uni.fmi.Solaris.models.Product;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends BaseDTO<Product>{
    private String name;
    private double price;
    private String description;
    private int quantity;
    private long categoryId;

    public ProductDTO(final Product product){
        super(product);
    }

    @Override
    protected void convertToDTO(Product entity) {
        setId(entity.getId());
        setName(entity.getName());
        setDescription(entity.getDescription());
        setPrice(entity.getPrice());
        setQuantity(entity.getQuantity());
        setCategoryId(entity.getCategory().getId());
    }
}
