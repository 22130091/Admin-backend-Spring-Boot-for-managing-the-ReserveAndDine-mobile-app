package com.admin.pos.Reservation.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.admin.pos.ReservationTable.dto.response.ReservationTableResponse;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {
  private Long reservationId;
  private Long userId;
  private String bookingCode;
  private String contactName;
  private String contactEmail;
  private String contactPhone;
  private Integer totalGuests;
  private String reservationStatus;
  private Boolean otpVerified;
  private LocalDateTime createdAt;
  private List<ReservationTableResponse> reservationTables;
}
