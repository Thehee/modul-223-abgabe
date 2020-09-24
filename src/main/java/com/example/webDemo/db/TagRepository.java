package com.example.webDemo.db;

import com.example.webDemo.db.dbo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
