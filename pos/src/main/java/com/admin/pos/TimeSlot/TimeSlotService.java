package com.admin.pos.TimeSlot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotService {

  @Autowired
  private TimeSlotRepository repository;

  public List<TimeSlot> getAll() {
    return repository.findAll();
  }

  public List<String> getAllLabel() {
    return repository.getAllLabel();
  }
}
