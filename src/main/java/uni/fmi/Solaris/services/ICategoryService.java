package uni.fmi.Solaris.services;

import uni.fmi.Solaris.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getAll();
    /**
    * Find Category by id
     * @param id
     * @return CategoryDTO
     *
    **/
    CategoryDTO getBy(Long id);

    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO);
    boolean delete(long id);
}
