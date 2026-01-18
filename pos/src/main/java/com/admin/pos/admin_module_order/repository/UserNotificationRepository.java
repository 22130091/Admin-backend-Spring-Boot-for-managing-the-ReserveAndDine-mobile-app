package com.admin.pos.admin_module_order.repository;

import com.admin.pos.admin_module_order.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, Integer> {
    List<UserNotification> findByUserId(Integer userId);
    List<UserNotification> findByUserIdAndIsRead(Integer userId, Boolean isRead);
    Long countByUserIdAndIsRead(Integer userId, Boolean isRead);
}
