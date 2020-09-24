package com.example.webDemo.db;

import com.example.webDemo.db.dbo.DemoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<DemoUser, Long> {

  Optional<DemoUser> findByUsername(String username);
}
