package com.admin.pos.admin_module_order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNotificationDTO {
    private Integer id;
    private Integer userId;
    private Integer notificationId;
    private Boolean isRead;
    private LocalDateTime readAt;
    private LocalDateTime deliveredAt;
    private NotificationDTO notification;
}
