package uni.fmi.Solaris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.Solaris.dto.BaseDTO;
import uni.fmi.Solaris.dto.ProductDTO;
import uni.fmi.Solaris.models.Category;
import uni.fmi.Solaris.models.Product;
import uni.fmi.Solaris.repo.ProductRepo;

import java.util.Optional;

@Service
public class ProductService extends BaseService<Product>{
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryService categoryService;
    @Override
    protected JpaRepository<Product, Long> getRepo() {
        return productRepo;
    }

    @Override
    protected Product convertDTOtoModel(BaseDTO<Product> baseDTO) {
        ProductDTO productDTO = (ProductDTO)baseDTO;
        Product product = new Product();

        margeProduct(productDTO, product);
        return product;
    }

    @Override
    protected void updateEntity(Product entity, BaseDTO<Product> productBaseDTO) {
        ProductDTO productDTO = (ProductDTO)productBaseDTO;
        Product product = getEntity(productDTO.getId()).get();

        margeProduct(productDTO, product);
    }

    private void margeProduct(ProductDTO productDTO, Product product) {
        long categoryId = productDTO.getCategoryId();
        Optional<Category> category = categoryService.getEntity(categoryId);
        if(category.isEmpty()){
            String errorMessage = "The category with id: '"
                    + categoryId + "' does not exists!";
            throw new IllegalStateException(errorMessage);
        }
        product.setCategory(category.get());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
    }

    @Override
    protected BaseDTO<Product> convert(Product entity) {
        return new ProductDTO(entity);
    }
}
