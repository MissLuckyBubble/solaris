package uni.fmi.Solaris.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.fmi.Solaris.dto.CategoryDTO;
import uni.fmi.Solaris.models.Category;
import uni.fmi.Solaris.repo.CategoryRepo;

@Component
public class DataGenerator {
    @Autowired
    private CategoryRepo categoryRepo;

    @PostConstruct
    public void initCategories() {
        if (categoryRepo.count() == 0) {
            Category category = new Category();
            category.setName("Category 1");
            category.setVatPercent(12);
            categoryRepo.save(category);

            Category category2 = new Category();
            category2.setName("Category 2");
            category2.setVatPercent(21);
            categoryRepo.save(category2);
        }
    }
}
