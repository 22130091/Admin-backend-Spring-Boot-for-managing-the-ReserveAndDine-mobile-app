package com.admin.pos.admin_module_order.service;

import com.admin.pos.admin_module_order.entity.Notification;
import com.admin.pos.admin_module_order.entity.Order;

public interface NotificationService {
    /**
     * Create a notification for order status change
     * @param userId the user to notify
     * @param orderId the order that changed
     * @param newStatus the new order status
     */
    void createOrderStatusNotification(Integer userId, Integer orderId, Order.OrderStatus newStatus);

    /**
     * Create a notification for order payment status change
     * @param userId the user to notify
     * @param orderId the order that changed
     * @param newPaymentStatus the new payment status
     */
    void createOrderPaymentNotification(Integer userId, Integer orderId, Order.PaymentStatus newPaymentStatus);

    /**
     * Create a generic notification and send it to a user
     * @param userId the user to notify
     * @param notification the notification to create
     */
    void createAndSendNotification(Integer userId, Notification notification);
}
