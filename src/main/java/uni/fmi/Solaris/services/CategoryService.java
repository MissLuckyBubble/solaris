package uni.fmi.Solaris.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uni.fmi.Solaris.dto.CategoryDTO;
import uni.fmi.Solaris.models.Category;
import uni.fmi.Solaris.repo.CategoryRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryService implements ICategoryService {

    CategoryRepo categoryRepo;

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepo.findAll();

       /* List<CategoryDTO> result = new ArrayList<>();
        for (Category cat:categories) {
            result.add(new CategoryDTO(cat));
        }
        return result;*/

        return categories
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    public CategoryDTO getBy(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        //Category byId = categoryRepo.findBy(id);
        final CategoryDTO result;
        if(category.isPresent()){
            result = new CategoryDTO(category.get());
        }else{
            result = null;
        }
        return result;
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO){
        Category category = convertDTOtoModel(categoryDTO);
        Category savedCategory = categoryRepo.save(category);
        return new CategoryDTO(savedCategory);
    }

    public CategoryDTO update(CategoryDTO categoryDTO){
        long id = categoryDTO.getId();
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setName(categoryDTO.getName());
            category.setVatPercent(categoryDTO.getVatPercent());
            return new CategoryDTO(categoryRepo.save(category));
        }
        return null;
    }

    @Override
    public boolean remove(long id) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if(optionalCategory.isPresent()){
            categoryRepo.delete(optionalCategory.get());
            return true;
        }
        return false;
    }

    private  Category convertDTOtoModel(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setVatPercent(categoryDTO.getVatPercent());
        category.setName(categoryDTO.getName());
        return category;
    }
    
}
