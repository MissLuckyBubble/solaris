package uni.fmi.Solaris.services;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uni.fmi.Solaris.dto.BaseDTO;
import uni.fmi.Solaris.dto.CategoryDTO;
import uni.fmi.Solaris.models.Category;
import uni.fmi.Solaris.repo.CategoryRepo;

@AllArgsConstructor
@Service
public class CategoryService extends BaseService<Category>{

    CategoryRepo categoryRepo;

    @Override
    protected JpaRepository<Category, Long> getRepo() {
        return categoryRepo;
    }

    @Override
    protected Category convertDTOtoModel(BaseDTO<Category> baseDTO) {
        Category category = new Category();

        CategoryDTO categoryDTO = (CategoryDTO)baseDTO;
        category.setVatPercent(categoryDTO.getVatPercent());
        category.setName(categoryDTO.getName());
        return category;
    }

    @Override
    protected void updateEntity(Category entity, BaseDTO<Category> categoryDTO) {
        CategoryDTO dto = (CategoryDTO)categoryDTO;
        entity.setVatPercent(dto.getVatPercent());
        entity.setName(dto.getName());
    }

    @Override
    protected BaseDTO<Category> convert(Category entity) {
        return new CategoryDTO(entity);
    }




}
