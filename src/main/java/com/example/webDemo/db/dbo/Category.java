package com.example.webDemo.db.dbo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long id;

   @Column
   private String name;
}
