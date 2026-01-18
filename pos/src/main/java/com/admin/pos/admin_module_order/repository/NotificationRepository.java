package com.admin.pos.admin_module_order.repository;

import com.admin.pos.admin_module_order.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByType(Notification.NotificationType type);
    List<Notification> findByPriority(Notification.NotificationPriority priority);
}
