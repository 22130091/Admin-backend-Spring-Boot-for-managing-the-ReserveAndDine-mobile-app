package com.admin.pos.TimeSlot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timeslots")
public class TimeSlotController {

  @Autowired
  private TimeSlotService service;

  @GetMapping
  public List<TimeSlot> getAll() {
    return service.getAll();
  }

  @GetMapping("/all-label")
  public List<String> getAllLabel() {
    return service.getAllLabel();
  }

}
