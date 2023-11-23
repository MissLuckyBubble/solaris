package uni.fmi.Solaris.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.Solaris.dto.CategoryDTO;
import uni.fmi.Solaris.models.Category;
import uni.fmi.Solaris.services.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(path = "/category")
public class CategoryRestController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    private CategoryRestController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<CategoryDTO> list() {
        List<Category> categories = categoryService.findAll();
        return categories
                .stream()
                .map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{categoryId}")
    public CategoryDTO getCategory(@PathVariable(name = "categoryId") long categoryId) {

        Optional<Category> optionalCategory = categoryService.getEntity(categoryId);
       /* if(optionalCategory.isPresent()){
            result = convertToCategoryDTO(optionalCategory.get());
        }else{
            result = null;
        }
        return result;*/
        return optionalCategory.map(this::convertToCategoryDTO).orElse(null);
    }

    private CategoryDTO convertToCategoryDTO(Category category) {
        final CategoryDTO result = modelMapper.map(category, CategoryDTO.class);
        return result;
    }

    @PostMapping()
    public CategoryDTO create(@RequestBody CategoryDTO newCategory) {
        Category category = convertCategoryDtoToModel(newCategory);
        return convertToCategoryDTO(categoryService.create(category));
    }

    private Category convertCategoryDtoToModel(CategoryDTO newCategory) {
        Category category = modelMapper.map(newCategory, Category.class);
        return category;
    }

    @PutMapping()
    public CategoryDTO update(@RequestBody CategoryDTO newCategory) {
        Category category = convertCategoryDtoToModel(newCategory);
        return convertToCategoryDTO(categoryService.update(category));
    }

    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity<String> remove(@PathVariable(name = "categoryId") long categoryId) {

        boolean isRemoved = categoryService.remove(categoryId);

        String deletedMessage = "Category with id: '" + categoryId + "' was deleted!";
        String notDeletedMessage = "Category with id: '" + categoryId + "' does not exists!";
        return isRemoved ?
                new ResponseEntity(deletedMessage, HttpStatusCode.valueOf(200)):
                new ResponseEntity(notDeletedMessage, HttpStatusCode.valueOf(404));
    }
}
