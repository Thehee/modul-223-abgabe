package com.example.webDemo.db;

import com.example.webDemo.db.dbo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
