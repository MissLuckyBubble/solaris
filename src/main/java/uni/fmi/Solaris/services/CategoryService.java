package uni.fmi.Solaris.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uni.fmi.Solaris.dto.CategoryDTO;
import uni.fmi.Solaris.models.Category;
import uni.fmi.Solaris.repo.CategoryRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryService implements ICategoryService {

    CategoryRepo categoryRepo;

    @Override
    public List<CategoryDTO> getAll(){
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

}
