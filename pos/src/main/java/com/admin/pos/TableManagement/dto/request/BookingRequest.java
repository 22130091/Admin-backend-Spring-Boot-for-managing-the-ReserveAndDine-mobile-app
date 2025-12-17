package com.admin.pos.TableManagement.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookingRequest {
  private String name;
  private String email;
  private String phone;
  private Integer totalGuests;

  private LocalDate date;
  private Long slotId;
}
