package uni.fmi.Solaris.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.Solaris.dto.BaseDTO;
import uni.fmi.Solaris.dto.ProductDTO;
import uni.fmi.Solaris.dto.UserDTO;
import uni.fmi.Solaris.models.Product;
import uni.fmi.Solaris.models.User;
import uni.fmi.Solaris.services.ProductService;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public List<BaseDTO<Product>> findAll(){
        return productService.getAll();
    }
    @GetMapping(path = "/{productId}")
    public BaseDTO<Product> getBy(@PathVariable(name = "productId") long productId){
        return productService.getBy(productId);
    }



    @PostMapping()
    public BaseDTO<Product> create(@RequestBody ProductDTO productDTO) {
        return productService.create(productDTO);
    }

    @PutMapping()
    public BaseDTO<Product> update(@RequestBody ProductDTO productDTO) {
        return productService.update(productDTO);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<String> remove(@PathVariable(name = "productId") long productId) {

        boolean isRemoved = productService.remove(productId);

        String deletedMessage = "Product with id: '" + productId + "' was deleted!";
        String notDeletedMessage = "Product with id: '" + productId + "' does not exists!";
        return isRemoved ?
                new ResponseEntity<>(deletedMessage, HttpStatusCode.valueOf(200)):
                new ResponseEntity<>(notDeletedMessage, HttpStatusCode.valueOf(404));
    }
}
