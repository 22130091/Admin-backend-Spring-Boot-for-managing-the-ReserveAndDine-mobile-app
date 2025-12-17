package com.admin.pos.TableManagement;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dining_tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiningTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tableId;

  @Column(nullable = false, unique = true)
  private String tableCode;

  @Column(nullable = false)
  private Integer seatingCapacity;

  private String area;

  private String notes;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", length = 20, nullable = false)
  private TableStatus status = TableStatus.EMPTY;

  @Column(name = "created_at")
  private LocalDateTime createdAt = LocalDateTime.now();
}
