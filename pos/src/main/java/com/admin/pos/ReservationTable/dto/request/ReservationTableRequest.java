package com.admin.pos.ReservationTable.dto.request;

import java.time.LocalDate;

import com.admin.pos.TableManagement.TableStatus;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationTableRequest {
  private Long reservationId;
  private Long tableId;
  private LocalDate date;
  private Long slotId; // vì TimeSlot là entity → request phải gửi slot_id
  private Integer guestsAtTable;
  private TableStatus reservationTableStatus;
}
