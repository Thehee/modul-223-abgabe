package com.example.webDemo.db;

import com.example.webDemo.db.dbo.Timespan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimespanRepository extends JpaRepository<Timespan, Long> {
}
