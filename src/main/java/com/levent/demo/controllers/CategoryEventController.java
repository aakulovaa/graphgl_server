package com.levent.demo.controllers;

import com.levent.demo.models.CategoryEvent;
import com.levent.demo.models.CityEvent;
import com.levent.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryEventController {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryEventController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @QueryMapping
    public List<CategoryEvent> allCategories(){
        return categoryRepository.findAll();
    }
    @QueryMapping
    public CategoryEvent oneCategory(@Argument Integer idCategory){
        Optional<CategoryEvent> categoryEventOptional = categoryRepository.findById(idCategory);
        if (categoryEventOptional.isEmpty()){
            throw new RuntimeException("Category not found");
        }
        return categoryEventOptional.get();
    }
    @MutationMapping
    public CategoryEvent createCategory(@Argument String nameCategory){
        CategoryEvent categoryEvent = new CategoryEvent();
        categoryEvent.setNameCategory(nameCategory);
        return categoryRepository.save(categoryEvent);
    }

    @MutationMapping
    public CategoryEvent updateCategory(@Argument Integer idCategory,@Argument String nameCategory){
        CategoryEvent categoryEvent = categoryRepository.findById(idCategory).orElseThrow(()->new RuntimeException("Category not found"));
        if (nameCategory != null){
            categoryEvent.setNameCategory(nameCategory);
        }

        return categoryRepository.save(categoryEvent);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Integer idCategory){
        categoryRepository.deleteById(idCategory);
        return true;
    }

}
