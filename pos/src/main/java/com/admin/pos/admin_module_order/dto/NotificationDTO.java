package com.admin.pos.admin_module_order.dto;

import com.admin.pos.admin_module_order.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
    private Integer id;
    private String title;
    private String message;
    private Notification.NotificationType type;
    private Notification.NotificationPriority priority;
    private LocalDateTime createdAt;
}
