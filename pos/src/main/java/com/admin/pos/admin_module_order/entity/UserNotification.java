package com.admin.pos.admin_module_order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_notification_id")
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "notification_id", nullable = false)
    private Integer notificationId;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    @Column(name = "read_at")
    private LocalDateTime readAt;

    @CreationTimestamp
    @Column(name = "delivered_at", updatable = false)
    private LocalDateTime deliveredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id", insertable = false, updatable = false)
    private Notification notification;
}
