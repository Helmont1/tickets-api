package com.upx.ticketsapi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upx.ticketsapi.payload.CategoryDTO;
import com.upx.ticketsapi.service.CategoryService;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RolesAllowed("get-categories")
    @GetMapping("/{categoryId}")
    public CategoryDTO getById(@PathVariable Integer categoryId) {
        return categoryService.getById(categoryId);
    }

    @RolesAllowed("get-categories")
    @GetMapping("/department/{departmentId}")
    public Page<CategoryDTO> getAllByDepartmentId(@PathVariable Integer departmentId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "categoryId") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        return categoryService.getAllByDepartmentId(departmentId,
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy)));
    }

    @RolesAllowed("get-categories")
    @GetMapping("/department/{departmentId}/active")
    public List<CategoryDTO> getAllActiveByDepartmentId(@PathVariable Integer departmentId) {
        return categoryService.getAllActiveByDepartmentId(departmentId);
    }

    @RolesAllowed("create-categories")
    @PostMapping
    public CategoryDTO create(@RequestBody CategoryDTO dto) {
        return categoryService.create(dto);
    }

    @RolesAllowed("update-categories")
    @PutMapping
    public CategoryDTO update(@RequestBody CategoryDTO dto) {
        return categoryService.update(dto);
    }

    @RolesAllowed("deactivate-categories")
    @PutMapping("/status/{categoryId}")
    public CategoryDTO updateStatus(@PathVariable Integer categoryId) {
        return categoryService.updateStatus(categoryId);
    }
}
