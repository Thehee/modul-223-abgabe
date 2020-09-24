package com.example.webDemo.controller;

import com.example.webDemo.db.dbo.DemoUser;
import com.example.webDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * Returns all users.
   * @return List of users
   */
  @GetMapping
  public List<DemoUser> getUsers() {
    return userService.getAll();
  }

  /**
   * Returns one user.
   * @param id of user
   * @return one user
   */
  @GetMapping(path = "/{id}")
  public DemoUser getOne(@PathVariable Long id) {
    return userService.getOne(id);
  }

  /**
   * Creates one demoUser
   * @param demoUser to be created
   */
  @PostMapping
  public void createUser(@RequestBody DemoUser demoUser) {
    userService.createUser(demoUser);
  }

  /**
   * Deletes one demoUser.
   * @param id from demoUser to be deleted
   */
  @DeleteMapping(path = "/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUserById(id);
  }

  /**
   * Edits one demoUser.
   * @param demoUser with new data.
   */
  @PutMapping
  public void editUser(@RequestBody DemoUser demoUser) {
    userService.editUser(demoUser);
  }
}
