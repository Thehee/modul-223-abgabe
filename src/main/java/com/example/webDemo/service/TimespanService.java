package com.example.webDemo.service;

import com.example.webDemo.db.TimespanRepository;
import com.example.webDemo.db.dbo.Timespan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class TimespanService {
   @Autowired
   private TimespanRepository timespanRepository;

   public void createTimespan(Timespan timespan) {
      timespanRepository.save(timespan);
   }

   public void editTimespan(Timespan timespan) {
      if (timespanRepository.findById(timespan.getId()).isEmpty()) {
         throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This Timespan doesn't exist");
      }

      timespanRepository.save(timespan);
   }

   public Timespan getTimespanById(Long id) {
      return timespanRepository.findById(id)
          .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This timespan does not exist"));
   }

   public List<Timespan> getTimespans() {
      return timespanRepository.findAll();
   }

   public void deleteById(Long id) {
      timespanRepository.deleteById(id);
   }
}
