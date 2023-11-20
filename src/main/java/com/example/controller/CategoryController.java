package com.example.controller;

import com.example.payloads.CategoryDto;
import com.example.service.CategoryService;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@Controller("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Post("/create")
    public CategoryDto createCategory(@Valid @Body CategoryDto categoryDto){
        return this.categoryService.createCategory(categoryDto);
    }
    @Put("/update/{categoryId}")
    public CategoryDto updateCategory(@Valid @Body CategoryDto categoryDto,@PathVariable Long categoryId){
        return this.categoryService.updateCategory(categoryDto,categoryId);
    }
    @Get("/get/{categoryId}")
    public CategoryDto getCategory(@PathVariable Long categoryId){
        return this.categoryService.getCategory(categoryId);
    }
    @Get("/getall")
    public List<CategoryDto> getCategories(){
        return categoryService.getCategories();
    }
    @Delete("/delete/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        this.categoryService.deleteCategory(categoryId);
    }
}
