package uni.fmi.Solaris.dto;

import lombok.NoArgsConstructor;
import uni.fmi.Solaris.models.Category;

import java.util.List;
@NoArgsConstructor
public class CategoryDTO extends BaseDTO<Category> {

    private String name;
    private CategoryDTO parent;
    private List<CategoryDTO> children;
    private int vatPercent;

    public CategoryDTO(Category category) {
        super(category);
    }

    @Override
    protected void convertToDTO(Category entity) {
        this.setId(entity.getId());
        this.name = entity.getName();
        this.vatPercent = entity.getVatPercent();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDTO getParent() {
        return parent;
    }

    public void setParent(CategoryDTO parent) {
        this.parent = parent;
    }

    public List<CategoryDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryDTO> children) {
        this.children = children;
    }

    public int getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(int vatPercent) {
        this.vatPercent = vatPercent;
    }
}
