package uni.fmi.Solaris.dto;

import uni.fmi.Solaris.models.Category;

import java.util.List;

public class CategoryDTO {
    private long id;
    private String name;
    private CategoryDTO parent;
    private List<CategoryDTO> children;
    private int vatPercent;

    public CategoryDTO() {
    }
    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.vatPercent = category.getVatPercent();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
