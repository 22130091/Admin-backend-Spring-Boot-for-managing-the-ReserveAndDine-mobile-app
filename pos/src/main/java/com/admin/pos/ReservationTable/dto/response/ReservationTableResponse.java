package com.admin.pos.ReservationTable.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.admin.pos.TableManagement.TableStatus;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationTableResponse {
  private Long reservationTableId;
  private Long reservationId;
  private Long tableId;
  private LocalDate date;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private Integer guestsAtTable;
  private TableStatus reservationTableStatus;
}
