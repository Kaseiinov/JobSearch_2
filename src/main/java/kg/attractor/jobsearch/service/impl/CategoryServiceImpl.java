package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.CategoryDao;
import kg.attractor.jobsearch.dto.CategoryDto;
import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryDao.findAll();

        return categoryBuilder(categories);
    }

    public List<CategoryDto> categoryBuilder(List<Category> categoryList){
        return categoryList
                .stream()
                .map(e -> CategoryDto
                        .builder()
                        .id(e.getId())
                        .name(e.getName())
                        .parentId(e.getParentId())
                        .build())
                .toList();
    }
}
