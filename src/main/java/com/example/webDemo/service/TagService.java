package com.example.webDemo.service;

import com.example.webDemo.db.TagRepository;
import com.example.webDemo.db.dbo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class TagService {

   @Autowired
   private TagRepository tagRepository;

   public void createTag(Tag tag) {
      tagRepository.save(tag);
   }

   public void editTag(Tag tag) {
      if (tagRepository.findById(tag.getId()).isEmpty()) {
         throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This Tag doesn't exist");
      }

      tagRepository.save(tag);
   }

   public Tag getTagById(Long id) {
      return tagRepository.findById(id)
          .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This tag does not exist"));
   }

   public List<Tag> getTags() {
      return tagRepository.findAll();
   }

   public void deleteById(Long id) {
      tagRepository.deleteById(id);
   }
}
