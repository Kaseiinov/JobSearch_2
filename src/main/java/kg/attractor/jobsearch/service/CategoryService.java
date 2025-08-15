package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.CategoryDto;
import kg.attractor.jobsearch.model.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategories();

    Category findModelCategoryById(Long id);
}
