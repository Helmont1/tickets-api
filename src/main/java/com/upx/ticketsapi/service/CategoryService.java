package com.upx.ticketsapi.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.Category;
import com.upx.ticketsapi.payload.CategoryDTO;
import com.upx.ticketsapi.repository.CategoryRepository;
import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private static final String CATEGORY_NOT_FOUND_MSG = "Category with id %s";

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO getById(Integer categoryId) {
        return fromDTO(categoryRepository.findById(categoryId).orElseThrow(
                () -> new NotFoundException(String.format(CATEGORY_NOT_FOUND_MSG, categoryId))), CategoryDTO.class);
    }

    public Page<CategoryDTO> getAllByDepartmentId(Integer departmentId, Pageable pageable) {
        return categoryRepository.findAllByDepartmentId(pageable, departmentId)
                .map(category -> fromDTO(category, CategoryDTO.class));
    }

    public List<CategoryDTO> getAllActiveByDepartmentId(Integer departmentId) {
        return categoryRepository.findAllActiveByDepartmentId(departmentId)
                .stream()
                .map(category -> fromDTO(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    public CategoryDTO create(CategoryDTO dto) {
        return fromDTO(categoryRepository.save(fromDTO(dto, Category.class)), CategoryDTO.class);
    }

    public CategoryDTO update(CategoryDTO dto) {
        var category = fromDTO(dto, Category.class);
        var categoryFromDb = fromDTO(getById(category.getCategoryId()), Category.class);
        BeanUtils.copyProperties(category, categoryFromDb, "categoryId", "department");
        return fromDTO(categoryRepository.save(categoryFromDb), CategoryDTO.class);
    }

    public CategoryDTO updateStatus(Integer categoryId) {
        var category = fromDTO(getById(categoryId), Category.class);
        category.setActive(!category.getActive());
        return fromDTO(categoryRepository.save(category), CategoryDTO.class);
    }

}
