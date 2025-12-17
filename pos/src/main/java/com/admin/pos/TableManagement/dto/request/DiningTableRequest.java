package com.admin.pos.TableManagement.dto.request;

import com.admin.pos.TableManagement.TableStatus;

import lombok.*;

@Data
public class DiningTableRequest {
  private String tableCode;
  private Integer seatingCapacity;
  private String area;
  private String notes;
  private TableStatus status;
}
