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

   /**
    * Returns all tags.
    * @return List of tags
    */
   @GetMapping
   public List<Tag> getTags() {
      return tagService.getTags();
   }

   /**
    * Returns one tag.
    * @param id of tag
    * @return one tag
    */
   @GetMapping(path = "/{id}")
   public Tag getOne(@PathVariable Long id) {
      return tagService.getTagById(id);
   }

   /**
    * Creates one tag
    * @param tag to be created
    */
   @PostMapping
   public void createTag(@RequestBody Tag tag) {
      tagService.createTag(tag);
   }

   /**
    * Deletes one tag.
    * @param id from tag to be deleted
    */
   @DeleteMapping(path = "/{id}")
   public void deleteTag(@PathVariable Long id) {
      tagService.deleteById(id);
   }

   /**
    * Edits one tag.
    * @param tag with new data.
    */
   @PutMapping
   public void editTag(@RequestBody Tag tag) {
      tagService.editTag(tag);
   }
}
