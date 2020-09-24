package com.example.webDemo.db.dbo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Timespan {
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long id;

   @Column
   private LocalDateTime start;

   @Column
   private LocalDateTime end;

   @ManyToOne
   private Category category;

   @ManyToMany
   private List<Tag> tags;
}
