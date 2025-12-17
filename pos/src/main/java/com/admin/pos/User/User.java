package com.admin.pos.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.admin.pos.Reservation.Reservation;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String passwordHash;

  private String fullName;

  @Column(unique = true)
  private String phone;

  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private Gender gender = Gender.Other;

  private LocalDate dob;

  @Enumerated(EnumType.STRING)
  @Column(length = 15)
  private Role role = Role.customer;

  @Enumerated(EnumType.STRING)
  @Column(length = 15)
  private Status status = Status.active;

  private LocalDateTime createdAt = LocalDateTime.now();
  private LocalDateTime updatedAt = LocalDateTime.now();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Reservation> reservations;

  public enum Gender {
    Male, Female, Other
  }

  public enum Role {
    customer, staff, manager, admin
  }

  public enum Status {
    active, inactive, banned
  }

  // Thêm constructor này
  public User(Long userId) {
    this.userId = userId;
  }
}
