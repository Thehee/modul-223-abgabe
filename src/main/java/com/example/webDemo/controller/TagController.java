package com.example.webDemo.controller;

import com.example.webDemo.db.dbo.Tag;
import com.example.webDemo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Tag Controller
 */
@RestController
@RequestMapping("/tag")
public class TagController {
   
   @Autowired
   private TagService tagService;

   @GetMapping
   public List<Tag> getTags() {
      return tagService.getTags();
   }

   @GetMapping(path = "/{id}")
   public Tag getOne(@PathVariable Long id) {
      return tagService.getTagById(id);
   }

   @PostMapping
   public void createTag(@RequestBody Tag demoTag) {
      tagService.createTag(demoTag);
   }

   @DeleteMapping(path = "/{id}")
   public void deleteTag(@PathVariable Long id) {
      tagService.deleteById(id);
   }

   @PutMapping
   public void editTag(@RequestBody Tag demoTag) {
      tagService.editTag(demoTag);
   }
}
