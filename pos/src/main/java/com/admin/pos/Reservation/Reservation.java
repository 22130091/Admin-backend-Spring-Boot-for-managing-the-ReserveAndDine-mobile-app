package com.admin.pos.Reservation;

import java.time.LocalDateTime;
import java.util.List;

import com.admin.pos.ReservationTable.ReservationTable;
import com.admin.pos.User.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reservation_id")
  private Long reservationId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "booking_code", nullable = false, unique = true)
  private String bookingCode;

  @Column(name = "contact_name")
  private String contactName;

  @Column(name = "contact_phone")
  private String contactPhone;

  @Column(name = "contact_email")
  private String contactEmail;

  @Column(name = "total_guests", nullable = false)
  private Integer totalGuests;

  @Enumerated(EnumType.STRING)
  @Column(name = "reservation_status", length = 20)
  private ReservationStatus reservationStatus = ReservationStatus.PENDING;

  @Column(name = "otp_code")
  private String otpCode;

  @Column(name = "otp_verified")
  private Boolean otpVerified = false;

  @Column(name = "otp_expires_at")
  private LocalDateTime otpExpiresAt;

  @Column(name = "created_at")
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "updated_at")
  private LocalDateTime updatedAt = LocalDateTime.now();

  @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ReservationTable> reservationTables;
}