package com.admin.pos.ReservationTable;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.admin.pos.Reservation.Reservation;
import com.admin.pos.TableManagement.DiningTable;
import com.admin.pos.TimeSlot.TimeSlot;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reservation_tables", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "table_id", "date", "slot_id" })
})
public class ReservationTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reservation_table_id")
  private Long reservationTableId;

  @ManyToOne
  @JoinColumn(name = "reservation_id", nullable = false)
  private Reservation reservation;

  @ManyToOne
  @JoinColumn(name = "table_id", nullable = false)
  private DiningTable diningTable;

  @ManyToOne
  @JoinColumn(name = "slot_id", nullable = false)
  private TimeSlot timeSlot;

  @Column(name = "date", nullable = false)
  private LocalDate date; // ví dụ 2025-12-02

  @Column(name = "guests_at_table")
  private Integer guestsAtTable;

  @Enumerated(EnumType.STRING)
  @Column(name = "reservation_table_status", length = 20, nullable = false)
  private ReservationTableStatus reservationTableStatus = ReservationTableStatus.Reserved;

  @Column(name = "created_at")
  private LocalDateTime createdAt = LocalDateTime.now();
}
