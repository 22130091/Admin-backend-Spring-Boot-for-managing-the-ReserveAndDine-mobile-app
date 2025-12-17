package com.admin.pos.Reservation.dto.request;

import java.util.List;

import com.admin.pos.Reservation.ReservationStatus;
import com.admin.pos.ReservationTable.dto.request.ReservationTableRequest;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {
  private Long userId;
  private String bookingCode;
  private String contactName;
  private String contactEmail;
  private String contactPhone;
  private Integer totalGuests;
  private List<ReservationTableRequest> reservationTables;
  private ReservationStatus reservationStatus;
}
