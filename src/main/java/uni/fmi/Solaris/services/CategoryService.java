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


}
