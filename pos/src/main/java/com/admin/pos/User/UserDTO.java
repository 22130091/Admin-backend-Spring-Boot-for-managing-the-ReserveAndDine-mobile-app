package com.admin.pos.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private Long userId;
  private String email;
  private String fullName;
  private String phone;
  private String gender;
  private LocalDate dob;
  private String role;
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
