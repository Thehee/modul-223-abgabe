package com.example.webDemo.controller;

import com.example.webDemo.db.dbo.Timespan;
import com.example.webDemo.service.TimespanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Timespan Controller
 */
@RestController
@RequestMapping("/timespan")
public class TimespanController {

   @Autowired
   private TimespanService timespanService;

   /**
    * Returns all timespans.
    * @return List of timespans
    */
   @GetMapping
   public List<Timespan> getTimespans() {
      return timespanService.getTimespans();
   }

   /**
    * Returns one timespan.
    * @param id of timespan
    * @return one timespan
    */
   @GetMapping(path = "/{id}")
   public Timespan getOne(@PathVariable Long id) {
      return timespanService.getTimespanById(id);
   }

   /**
    * Creates one timespan
    * @param timespan to be created
    */
   @PostMapping
   public void createTimespan(@RequestBody Timespan timespan) {
      timespanService.createTimespan(timespan);
   }

   /**
    * Deletes one timespan.
    * @param id from timespan to be deleted
    */
   @DeleteMapping(path = "/{id}")
   public void deleteTimespan(@PathVariable Long id) {
      timespanService.deleteById(id);
   }

   /**
    * Edits one timespan.
    * @param timespan with new data.
    */
   @PutMapping
   public void editTimespan(@RequestBody Timespan timespan) {
      timespanService.editTimespan(timespan);
   }
}
