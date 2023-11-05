package uni.fmi.Solaris.services;

import uni.fmi.Solaris.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getAll();
}
