package com.example.webDemo.service;

import com.example.webDemo.db.CategoryRepository;
import com.example.webDemo.db.dbo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class CategoryService {
   @Autowired
   private CategoryRepository categoryRepository;

   public void createCategory(Category category) {
      categoryRepository.save(category);
   }

   public void editCategory(Category category) {
      if (categoryRepository.findById(category.getId()).isEmpty()) {
         throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This Category doesn't exist");
      }

      categoryRepository.save(category);
   }

   public Category getCategoryById(Long id) {
      return categoryRepository.findById(id)
          .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This category does not exist"));
   }

   public List<Category> getCategories() {
      return categoryRepository.findAll();
   }

   public void deleteById(Long id) {
      categoryRepository.deleteById(id);
   }
}
