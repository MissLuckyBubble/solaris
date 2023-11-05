package uni.fmi.Solaris.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.fmi.Solaris.dto.CategoryDTO;
import uni.fmi.Solaris.services.ICategoryService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping(path = "/category")
public class CategoryRestController {

    private ICategoryService categoryService;
    @Autowired
    private CategoryRestController(ICategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping(path = "/list")
    public List<CategoryDTO> list(){
        return categoryService.getAll();
    }
}
