package com.example.webDemo.controller;

import com.example.webDemo.db.dbo.DemoUser;
import com.example.webDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<DemoUser> getUsers() {
    return userService.getAll();
  }

  @GetMapping(path = "/{id}")
  public DemoUser getOne(@PathVariable Long id) {
    return userService.getOne(id);
  }

  @PostMapping
  public void createUser(@RequestBody DemoUser demoUser) {
    userService.createUser(demoUser);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUserById(id);
  }

  @PutMapping
  public void editUser(@RequestBody DemoUser demoUser) {
    userService.editUser(demoUser);
  }
}
