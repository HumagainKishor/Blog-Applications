package com.example.service;

import com.example.entity.Category;
import com.example.exceptions.ResourceNotFoundException;
import com.example.payloads.CategoryDto;
import com.example.repository.CategoryRepo;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.graalvm.nativebridge.In;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    @Inject
    private ModelMapper modelMapper;
    public CategoryDto createCategory(CategoryDto categoryDto){
        Category cat = this.modelMapper.map(categoryDto,Category.class);
        Category addedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,CategoryDto.class);
    }
    @Transactional
    public CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId){
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedcat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedcat,CategoryDto.class);
    }

    public void deleteCategory(Long categoryId){
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        this.categoryRepo.delete(cat);
    }
    public CategoryDto getCategory(Long categoryId){
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    public List<CategoryDto> getCategories(){
        List<Category > categories = this.categoryRepo.findAll();
        List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return catDtos;
    }

}
