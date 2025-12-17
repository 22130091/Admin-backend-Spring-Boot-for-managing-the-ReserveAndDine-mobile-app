package com.admin.pos.TableManagement.dto.response;

import java.time.LocalDateTime;
import com.admin.pos.TableManagement.TableStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiningTableResponse {
  private Long tableId;
  private String tableCode;
  private Integer seatingCapacity;
  private String area;
  private TableStatus status;
  private String notes;
  private LocalDateTime createdAt;
}
