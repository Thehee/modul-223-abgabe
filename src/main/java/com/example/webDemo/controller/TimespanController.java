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

   @GetMapping
   public List<Timespan> getTimespans() {
      return timespanService.getTimespans();
   }

   @GetMapping(path = "/{id}")
   public Timespan getOne(@PathVariable Long id) {
      return timespanService.getTimespanById(id);
   }

   @PostMapping
   public void createTimespan(@RequestBody Timespan demoTimespan) {
      timespanService.createTimespan(demoTimespan);
   }

   @DeleteMapping(path = "/{id}")
   public void deleteTimespan(@PathVariable Long id) {
      timespanService.deleteById(id);
   }

   @PutMapping
   public void editTimespan(@RequestBody Timespan demoTimespan) {
      timespanService.editTimespan(demoTimespan);
   }
}
