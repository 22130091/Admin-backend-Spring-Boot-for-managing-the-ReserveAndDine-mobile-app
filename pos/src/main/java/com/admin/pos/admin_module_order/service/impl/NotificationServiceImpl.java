package com.admin.pos.admin_module_order.service.impl;

import com.admin.pos.admin_module_order.entity.Notification;
import com.admin.pos.admin_module_order.entity.Order;
import com.admin.pos.admin_module_order.entity.UserNotification;
import com.admin.pos.admin_module_order.repository.NotificationRepository;
import com.admin.pos.admin_module_order.repository.UserNotificationRepository;
import com.admin.pos.admin_module_order.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;

    @Override
    @Transactional
    public void createOrderStatusNotification(Integer userId, Integer orderId, Order.OrderStatus newStatus) {
        if (userId == null || orderId == null || newStatus == null) {
            log.warn("Cannot create notification with null parameters: userId={}, orderId={}, status={}",
                    userId, orderId, newStatus);
            return;
        }

        String title = getOrderStatusTitle(newStatus);
        String message = getOrderStatusMessage(orderId, newStatus);

        Notification notification = Notification.builder()
                .title(title)
                .message(message)
                .type(Notification.NotificationType.order)
                .priority(getOrderStatusPriority(newStatus))
                .build();

        createAndSendNotification(userId, notification);

        log.info("Created order status notification for user {} - Order #{}: {}", userId, orderId, newStatus);
    }

    @Override
    @Transactional
    public void createOrderPaymentNotification(Integer userId, Integer orderId, Order.PaymentStatus newPaymentStatus) {
        if (userId == null || orderId == null || newPaymentStatus == null) {
            log.warn("Cannot create notification with null parameters: userId={}, orderId={}, paymentStatus={}",
                    userId, orderId, newPaymentStatus);
            return;
        }

        String title = getPaymentStatusTitle(newPaymentStatus);
        String message = getPaymentStatusMessage(orderId, newPaymentStatus);

        Notification notification = Notification.builder()
                .title(title)
                .message(message)
                .type(Notification.NotificationType.order)
                .priority(Notification.NotificationPriority.medium)
                .build();

        createAndSendNotification(userId, notification);

        log.info("Created payment status notification for user {} - Order #{}: {}", userId, orderId, newPaymentStatus);
    }

    @Override
    @Transactional
    public void createAndSendNotification(Integer userId, Notification notification) {
        // Save the notification
        Notification savedNotification = notificationRepository.save(notification);

        // Create user notification
        UserNotification userNotification = UserNotification.builder()
                .userId(userId)
                .notificationId(savedNotification.getId())
                .isRead(false)
                .build();

        userNotificationRepository.save(userNotification);
    }

    private String getOrderStatusTitle(Order.OrderStatus status) {
        return switch (status) {
            case pending -> "Đơn hàng đang chờ xử lý";
            case confirmed -> "Đơn hàng đã được xác nhận";
            case preparing -> "Đơn hàng đang được chuẩn bị";
            case ready -> "Đơn hàng đã sẵn sàng";
            case served -> "Đơn hàng đã được phục vụ";
            case cancelled -> "Đơn hàng đã bị hủy";
        };
    }

    private String getOrderStatusMessage(Integer orderId, Order.OrderStatus status) {
        return switch (status) {
            case pending -> String.format("Đơn hàng #%d của bạn đang chờ xác nhận.", orderId);
            case confirmed -> String.format("Đơn hàng #%d của bạn đã được xác nhận và sẽ sớm được chuẩn bị.", orderId);
            case preparing -> String.format("Đơn hàng #%d của bạn đang được chuẩn bị.", orderId);
            case ready -> String.format("Đơn hàng #%d của bạn đã sẵn sàng để phục vụ.", orderId);
            case served -> String.format("Đơn hàng #%d của bạn đã được phục vụ. Chúc bạn ngon miệng!", orderId);
            case cancelled -> String.format("Đơn hàng #%d của bạn đã bị hủy. Vui lòng liên hệ nhân viên nếu có thắc mắc.", orderId);
        };
    }

    private Notification.NotificationPriority getOrderStatusPriority(Order.OrderStatus status) {
        return switch (status) {
            case cancelled -> Notification.NotificationPriority.high;
            case ready, served -> Notification.NotificationPriority.medium;
            default -> Notification.NotificationPriority.low;
        };
    }

    private String getPaymentStatusTitle(Order.PaymentStatus status) {
        return switch (status) {
            case unpaid -> "Chờ thanh toán";
            case paid -> "Thanh toán thành công";
            case refunded -> "Đã hoàn tiền";
        };
    }

    private String getPaymentStatusMessage(Integer orderId, Order.PaymentStatus status) {
        return switch (status) {
            case unpaid -> String.format("Đơn hàng #%d đang chờ thanh toán.", orderId);
            case paid -> String.format("Cảm ơn bạn! Thanh toán cho đơn hàng #%d đã thành công.", orderId);
            case refunded -> String.format("Đơn hàng #%d đã được hoàn tiền vào tài khoản của bạn.", orderId);
        };
    }
}
