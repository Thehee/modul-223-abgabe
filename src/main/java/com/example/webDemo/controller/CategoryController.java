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

   @GetMapping
   public List<Category> getCategories() {
      return categoryService.getCategories();
   }

   @GetMapping(path = "/{id}")
   public Category getOne(@PathVariable Long id) {
      return categoryService.getCategoryById(id);
   }

   @PostMapping
   public void createCategory(@RequestBody Category demoCategory) {
      categoryService.createCategory(demoCategory);
   }

   @DeleteMapping(path = "/{id}")
   public void deleteCategory(@PathVariable Long id) {
      categoryService.deleteById(id);
   }

   @PutMapping
   public void editCategory(@RequestBody Category demoCategory) {
      categoryService.editCategory(demoCategory);
   }
}
