package com.example.webDemo.controller;

import com.example.webDemo.db.dbo.Category;
import com.example.webDemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Category Controller
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

   @Autowired
   private CategoryService categoryService;

   /**
    * Returns all categories.
    * @return List of categories
    */
   @GetMapping
   public List<Category> getCategories() {
      return categoryService.getCategories();
   }

   /**
    * Returns one category.
    * @param id of category
    * @return one category
    */
   @GetMapping(path = "/{id}")
   public Category getOne(@PathVariable Long id) {
      return categoryService.getCategoryById(id);
   }

   /**
    * Creates one category
    * @param category to be created
    */
   @PostMapping
   public void createCategory(@RequestBody Category category) {
      categoryService.createCategory(category);
   }

   /**
    * Deletes one category.
    * @param id from category to be deleted
    */
   @DeleteMapping(path = "/{id}")
   public void deleteCategory(@PathVariable Long id) {
      categoryService.deleteById(id);
   }

   /**
    * Edits one category.
    * @param category with new data.
    */
   @PutMapping
   public void editCategory(@RequestBody Category category) {
      categoryService.editCategory(category);
   }
}
